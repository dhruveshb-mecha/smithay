//! Common traits for input backends to receive input from.

use std::{error::Error, string::ToString};

/// A seat describes a group of input devices and at least one
/// graphics device belonging together.
///
/// By default only one seat exists for most systems and smithay backends
/// however multiseat configurations are possible and should be treated as
/// separated users, all with their own focus, input and cursor available.
///
/// Seats referring to the same internal id will always be equal and result in the same
/// hash, but capabilities of cloned and copied [`Seat`]s will not be updated by smithay.
/// Always refer to the [`Seat`] given by a callback for up-to-date information. You may
/// use this to calculate the differences since the last callback.
#[derive(Debug, Clone, Eq)]
pub struct Seat {
    id: u64,
    name: String,
    capabilities: SeatCapabilities,
}

impl Seat {
    pub(crate) fn new<S: ToString>(id: u64, name: S, capabilities: SeatCapabilities) -> Seat {
        Seat {
            id,
            name: name.to_string(),
            capabilities,
        }
    }

    pub(crate) fn capabilities_mut(&mut self) -> &mut SeatCapabilities {
        &mut self.capabilities
    }

    /// Get the currently capabilities of this [`Seat`]
    pub fn capabilities(&self) -> &SeatCapabilities {
        &self.capabilities
    }

    /// Get the name of this [`Seat`]
    pub fn name(&self) -> &str {
        &*self.name
    }
}

impl ::std::cmp::PartialEq for Seat {
    fn eq(&self, other: &Seat) -> bool {
        self.id == other.id
    }
}

impl ::std::hash::Hash for Seat {
    fn hash<H>(&self, state: &mut H)
    where
        H: ::std::hash::Hasher,
    {
        self.id.hash(state);
    }
}

/// Describes capabilities a [`Seat`] has.
#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash)]
pub struct SeatCapabilities {
    /// [`Seat`] has a pointer
    pub pointer: bool,
    /// [`Seat`] has a keyboard
    pub keyboard: bool,
    /// [`Seat`] has a touchscreen
    pub touch: bool,
}

/// Trait for generic functions every input event does provide
pub trait Event {
    /// Returns an upward counting variable useful for event ordering.
    ///
    /// Makes no guarantees about actual time passed between events.
    // # TODO:
    // - check if events can even arrive out of order.
    // - Make stronger time guarantees, if possible
    fn time(&self) -> u32;
}

/// Used to mark events never emitted by an [`InputBackend`] implementation.
///
/// Implements all event types and can be used in place for any [`Event`] type,
/// that is not used by an [`InputBackend`] implementation. Initialization is not
/// possible, making accidental use impossible and enabling a lot of possible
/// compiler optimizations.
pub enum UnusedEvent {}

impl Event for UnusedEvent {
    fn time(&self) -> u32 {
        match *self {}
    }
}

/// State of key on a keyboard. Either pressed or released
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
pub enum KeyState {
    /// Key is released
    Released,
    /// Key is pressed
    Pressed,
}

/// Trait for keyboard event
pub trait KeyboardKeyEvent: Event {
    /// Code of the pressed key. See `linux/input-event-codes.h`
    fn key_code(&self) -> u32;
    /// State of the key
    fn state(&self) -> KeyState;
    /// Total number of keys pressed on all devices on the associated [`Seat`]
    fn count(&self) -> u32;
}

impl KeyboardKeyEvent for UnusedEvent {
    fn key_code(&self) -> u32 {
        match *self {}
    }

    fn state(&self) -> KeyState {
        match *self {}
    }

    fn count(&self) -> u32 {
        match *self {}
    }
}

/// A particular mouse button
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
pub enum MouseButton {
    /// Left mouse button
    Left,
    /// Middle mouse button
    Middle,
    /// Right mouse button
    Right,
    /// Other mouse button with index
    Other(u8),
}

/// State of a button on a mouse. Either pressed or released
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
pub enum MouseButtonState {
    /// Button is released
    Released,
    /// Button is pressed
    Pressed,
}

/// Common methods pointer event generated by pressed buttons do implement
pub trait PointerButtonEvent: Event {
    /// Pressed button of the event
    fn button(&self) -> MouseButton;
    /// State of the button
    fn state(&self) -> MouseButtonState;
}

impl PointerButtonEvent for UnusedEvent {
    fn button(&self) -> MouseButton {
        match *self {}
    }

    fn state(&self) -> MouseButtonState {
        match *self {}
    }
}

/// Axis when scrolling
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
pub enum Axis {
    /// Vertical axis
    Vertical,
    /// Horizontal axis
    Horizontal,
}

/// Source of an axis when scrolling
#[derive(Debug, PartialEq, Eq, Clone, Copy)]
pub enum AxisSource {
    /// Finger. Mostly used for trackpads.
    ///
    /// Guarantees that a scroll sequence is terminated with a scroll value of 0.
    /// A caller may use this information to decide on whether kinetic scrolling should
    /// be triggered on this scroll sequence.
    ///
    /// The coordinate system is identical to the
    /// cursor movement, i.e. a scroll value of 1 represents the equivalent relative
    /// motion of 1.
    Finger,
    /// Continuous scrolling device. Almost identical to [`Finger`](AxisSource::Finger)
    ///
    /// No terminating event is guaranteed (though it may happen).
    ///
    /// The coordinate system is identical to
    /// the cursor movement, i.e. a scroll value of 1 represents the equivalent relative
    /// motion of 1.
    Continuous,
    /// Scroll wheel.
    ///
    /// No terminating event is guaranteed (though it may happen). Scrolling is in
    /// discrete steps. It is up to the caller how to interpret such different step sizes.
    Wheel,
    /// Scrolling through tilting the scroll wheel.
    ///
    /// No terminating event is guaranteed (though it may happen). Scrolling is in
    /// discrete steps. It is up to the caller how to interpret such different step sizes.
    WheelTilt,
}

/// Trait for pointer events generated by scrolling on an axis.
pub trait PointerAxisEvent: Event {
    /// Amount of scrolling in pixels on the given [`Axis`].
    ///
    /// Guaranteed to be `Some` when source returns either [`AxisSource::Finger`] or [`AxisSource::Continuous`].
    fn amount(&self, axis: Axis) -> Option<f64>;

    /// Amount of scrolling in discrete steps on the given [`Axis`].
    ///
    /// Guaranteed to be `Some` when source returns either [`AxisSource::Wheel`] or [`AxisSource::WheelTilt`].
    fn amount_discrete(&self, axis: Axis) -> Option<f64>;

    /// Source of the scroll event.
    fn source(&self) -> AxisSource;
}

impl PointerAxisEvent for UnusedEvent {
    fn amount(&self, _axis: Axis) -> Option<f64> {
        match *self {}
    }

    fn amount_discrete(&self, _axis: Axis) -> Option<f64> {
        match *self {}
    }

    fn source(&self) -> AxisSource {
        match *self {}
    }
}

/// Trait for pointer events generated by relative device movement.
pub trait PointerMotionEvent: Event {
    /// Delta between the last and new pointer device position interpreted as pixel movement
    fn delta(&self) -> (f64, f64) {
        (self.delta_x(), self.delta_y())
    }

    /// Delta on the x axis between the last and new pointer device position interpreted as pixel movement
    fn delta_x(&self) -> f64;
    /// Delta on the y axis between the last and new pointer device position interpreted as pixel movement
    fn delta_y(&self) -> f64;
}

impl PointerMotionEvent for UnusedEvent {
    fn delta_x(&self) -> f64 {
        match *self {}
    }

    fn delta_y(&self) -> f64 {
        match *self {}
    }
}

/// Trait for pointer events generated by absolute device positioning.
pub trait PointerMotionAbsoluteEvent: Event {
    /// Device position in it's original coordinate space.
    ///
    /// The format is defined by the backend implementation.
    fn position(&self) -> (f64, f64) {
        (self.x(), self.y())
    }

    /// Device x position in it's original coordinate space.
    ///
    /// The format is defined by the backend implementation.
    fn x(&self) -> f64;

    /// Device y position in it's original coordinate space.
    ///
    /// The format is defined by the backend implementation.
    fn y(&self) -> f64;

    /// Device position converted to the targets coordinate space.
    /// E.g. the focused output's resolution.
    fn position_transformed(&self, coordinate_space: (u32, u32)) -> (f64, f64) {
        (
            self.x_transformed(coordinate_space.0),
            self.y_transformed(coordinate_space.1),
        )
    }

    /// Device x position converted to the targets coordinate space's width.
    /// E.g. the focused output's width.
    fn x_transformed(&self, width: u32) -> f64;

    /// Device y position converted to the targets coordinate space's height.
    /// E.g. the focused output's height.
    fn y_transformed(&self, height: u32) -> f64;
}

impl PointerMotionAbsoluteEvent for UnusedEvent {
    fn x(&self) -> f64 {
        match *self {}
    }

    fn y(&self) -> f64 {
        match *self {}
    }

    fn x_transformed(&self, _width: u32) -> f64 {
        match *self {}
    }

    fn y_transformed(&self, _height: u32) -> f64 {
        match *self {}
    }
}

/// Slot of a different touch event.
///
/// Touch events are grouped by slots, usually to identify different
/// fingers on a multi-touch enabled input device. Events should only
/// be interpreted in the context of other events on the same slot.
#[derive(Debug, Clone, Copy, PartialEq, Eq, Hash)]
pub struct TouchSlot {
    id: u64,
}

impl TouchSlot {
    pub(crate) fn new(id: u64) -> Self {
        TouchSlot { id }
    }
}

/// Trait for touch events starting at a given position.
pub trait TouchDownEvent: Event {
    /// [`TouchSlot`], if the device has multi-touch capabilities
    fn slot(&self) -> Option<TouchSlot>;

    /// Touch position in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn position(&self) -> (f64, f64) {
        (self.x(), self.y())
    }

    /// Touch position converted into the target coordinate space.
    /// E.g. the focused output's resolution.
    fn position_transformed(&self, coordinate_space: (u32, u32)) -> (f64, f64) {
        (
            self.x_transformed(coordinate_space.0),
            self.y_transformed(coordinate_space.1),
        )
    }

    /// Touch event's x-coordinate in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn x(&self) -> f64;

    /// Touch event's x-coordinate in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn y(&self) -> f64;

    /// Touch event's x position converted to the targets coordinate space's width.
    /// E.g. the focused output's width.
    fn x_transformed(&self, width: u32) -> f64;

    /// Touch event's y position converted to the targets coordinate space's width.
    /// E.g. the focused output's width.
    fn y_transformed(&self, height: u32) -> f64;
}

impl TouchDownEvent for UnusedEvent {
    fn slot(&self) -> Option<TouchSlot> {
        match *self {}
    }

    fn x(&self) -> f64 {
        match *self {}
    }

    fn y(&self) -> f64 {
        match *self {}
    }

    fn x_transformed(&self, _width: u32) -> f64 {
        match *self {}
    }

    fn y_transformed(&self, _height: u32) -> f64 {
        match *self {}
    }
}

/// Trait for touch events regarding movement on the screen
pub trait TouchMotionEvent: Event {
    /// [`TouchSlot`], if the device has multi-touch capabilities
    fn slot(&self) -> Option<TouchSlot>;

    /// Touch position in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn position(&self) -> (f64, f64) {
        (self.x(), self.y())
    }

    /// Touch position converted into the target coordinate space.
    /// E.g. the focused output's resolution.
    fn position_transformed(&self, coordinate_space: (u32, u32)) -> (f64, f64) {
        (
            self.x_transformed(coordinate_space.0),
            self.y_transformed(coordinate_space.1),
        )
    }

    /// Touch event's x-coordinate in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn x(&self) -> f64;

    /// Touch event's x-coordinate in the device's native coordinate space
    ///
    /// The actual format is defined by the implementation.
    fn y(&self) -> f64;

    /// Touch event's x position converted to the targets coordinate space's width.
    /// E.g. the focused output's width.
    fn x_transformed(&self, width: u32) -> f64;

    /// Touch event's y position converted to the targets coordinate space's width.
    /// E.g. the focused output's width.
    fn y_transformed(&self, height: u32) -> f64;
}

impl TouchMotionEvent for UnusedEvent {
    fn slot(&self) -> Option<TouchSlot> {
        match *self {}
    }

    fn x(&self) -> f64 {
        match *self {}
    }

    fn y(&self) -> f64 {
        match *self {}
    }

    fn x_transformed(&self, _width: u32) -> f64 {
        match *self {}
    }

    fn y_transformed(&self, _height: u32) -> f64 {
        match *self {}
    }
}

/// Trait for touch events finishing.
pub trait TouchUpEvent: Event {
    /// [`TouchSlot`], if the device has multi-touch capabilities
    fn slot(&self) -> Option<TouchSlot>;
}

impl TouchUpEvent for UnusedEvent {
    fn slot(&self) -> Option<TouchSlot> {
        match *self {}
    }
}

/// Trait for touch events cancelling the chain
pub trait TouchCancelEvent: Event {
    /// [`TouchSlot`], if the device has multi-touch capabilities
    fn slot(&self) -> Option<TouchSlot>;
}

impl TouchCancelEvent for UnusedEvent {
    fn slot(&self) -> Option<TouchSlot> {
        match *self {}
    }
}

/// Trait for touch frame events
pub trait TouchFrameEvent: Event {}

impl TouchFrameEvent for UnusedEvent {}

/// Trait that describes objects providing a source of input events. All input backends
/// need to implement this and provide the same base guarantees about the precision of
/// given events.
pub trait InputBackend: Sized {
    /// Type representing errors that may be returned when processing events
    type EventError: Error;

    /// Type representing keyboard events
    type KeyboardKeyEvent: KeyboardKeyEvent;
    /// Type representing axis events on pointer devices
    type PointerAxisEvent: PointerAxisEvent;
    /// Type representing button events on pointer devices
    type PointerButtonEvent: PointerButtonEvent;
    /// Type representing motion events of pointer devices
    type PointerMotionEvent: PointerMotionEvent;
    /// Type representing motion events of pointer devices
    type PointerMotionAbsoluteEvent: PointerMotionAbsoluteEvent;
    /// Type representing touch events starting
    type TouchDownEvent: TouchDownEvent;
    /// Type representing touch events ending
    type TouchUpEvent: TouchUpEvent;
    /// Type representing touch events from moving
    type TouchMotionEvent: TouchMotionEvent;
    /// Type representing cancelling of touch events
    type TouchCancelEvent: TouchCancelEvent;
    /// Type representing touch frame events
    type TouchFrameEvent: TouchFrameEvent;

    /// Special events that are custom to this backend
    type SpecialEvent;

    /// Backend-specific type allowing you to configure it
    type InputConfig: ?Sized;

    /// Get the list of currently known Seats
    fn seats(&self) -> Vec<Seat>;

    /// Access the input configuration interface
    fn input_config(&mut self) -> &mut Self::InputConfig;

    /// Processes new events of the underlying backend and drives the [`InputHandler`].
    ///
    /// The callback can only assume its second argument to be usable if the event is
    /// `InputEvent::ConfigChanged`.
    fn dispatch_new_events<F>(&mut self, callback: F) -> Result<(), Self::EventError>
    where
        F: FnMut(InputEvent<Self>, &mut Self::InputConfig);
}

/// Different events that can be generated by an input backend
pub enum InputEvent<B: InputBackend> {
    /// A new seat has been created
    NewSeat(Seat),
    /// A seat has changed
    SeatChanged(Seat),
    /// A seat has been removed
    SeatRemoved(Seat),
    /// A keyboard event occured
    Keyboard {
        /// Seat that generated the event
        seat: Seat,
        /// The keyboard event
        event: B::KeyboardKeyEvent,
    },
    /// A relative pointer motion occured
    PointerMotion {
        /// Seat that generated the event
        seat: Seat,
        /// The pointer motion event
        event: B::PointerMotionEvent,
    },
    /// An absolute pointer motion occures
    PointerMotionAbsolute {
        /// Seat that generated the event
        seat: Seat,
        /// The absolute pointer motion event
        event: B::PointerMotionAbsoluteEvent,
    },
    /// A pointer button was pressed or released
    PointerButton {
        /// Seat that generated the event
        seat: Seat,
        /// The pointer button event
        event: B::PointerButtonEvent,
    },
    /// A pointer axis was actionned
    PointerAxis {
        /// Seat that generated the event
        seat: Seat,
        /// The pointer axis event
        event: B::PointerAxisEvent,
    },
    /// A new touchpoint appeared
    TouchDown {
        /// Seat that generated the event
        seat: Seat,
        /// The touch down event
        event: B::TouchDownEvent,
    },
    /// A touchpoint moved
    TouchMotion {
        /// Seat that generated the event
        seat: Seat,
        /// The touch motion event
        event: B::TouchMotionEvent,
    },
    /// A touchpoint was removed
    TouchUp {
        /// Seat that generated the event
        seat: Seat,
        /// The touch up event
        event: B::TouchUpEvent,
    },
    /// A touch sequence was cancelled
    TouchCancel {
        /// Seat that generated the event
        seat: Seat,
        /// The touch cancel event
        event: B::TouchCancelEvent,
    },
    /// A touch frame was emmited
    ///
    /// A set of two events received on the same seat between two frames should
    /// be interpreted as an atomic event.
    TouchFrame {
        /// Seat that generated the event
        seat: Seat,
        /// The touch frame event
        event: B::TouchFrameEvent,
    },
    /// Special event specific of this backend
    Special(B::SpecialEvent),
}
