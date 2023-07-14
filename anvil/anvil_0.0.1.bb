# Auto-Generated by cargo-bitbake 0.3.16
#
inherit cargo


# If this is git based prefer versioned ones if they exist
# DEFAULT_PREFERENCE = "-1"

# how to get anvil could be as easy as but default to a git checkout:
# SRC_URI += "crate://crates.io/anvil/0.0.1"
SRC_URI += "git://git@github.com/dhruveshb-mecha/smithay.git;protocol=ssh;nobranch=1"
SRCREV = "c8f2ddd58ac817f46fb468a90dd5e4045da0db33"
S = "${WORKDIR}/git"
CARGO_SRC_DIR = "anvil"
PV:append = ".AUTOINC+c8f2ddd58a"

# please note if you have entries that do not begin with crate://
# you must change them to how that package can be fetched
SRC_URI += " \
    crate://crates.io/adler/1.0.2 \
    crate://crates.io/aho-corasick/1.0.2 \
    crate://crates.io/android-activity/0.4.2 \
    crate://crates.io/android-properties/0.2.2 \
    crate://crates.io/anes/0.1.6 \
    crate://crates.io/anstream/0.3.2 \
    crate://crates.io/anstyle-parse/0.2.1 \
    crate://crates.io/anstyle-query/1.0.0 \
    crate://crates.io/anstyle-wincon/1.0.1 \
    crate://crates.io/anstyle/1.0.1 \
    crate://crates.io/anyhow/1.0.71 \
    crate://crates.io/appendlist/1.4.0 \
    crate://crates.io/approx/0.4.0 \
    crate://crates.io/ash/0.37.3+1.3.251 \
    crate://crates.io/atty/0.2.14 \
    crate://crates.io/autocfg/1.1.0 \
    crate://crates.io/bincode/1.3.3 \
    crate://crates.io/bit_field/0.10.2 \
    crate://crates.io/bitflags/1.3.2 \
    crate://crates.io/bitflags/2.3.3 \
    crate://crates.io/block-sys/0.1.0-beta.1 \
    crate://crates.io/block2/0.2.0-alpha.6 \
    crate://crates.io/bumpalo/3.13.0 \
    crate://crates.io/bytemuck/1.13.1 \
    crate://crates.io/bytemuck_derive/1.4.1 \
    crate://crates.io/byteorder/1.4.3 \
    crate://crates.io/calloop/0.10.6 \
    crate://crates.io/cast/0.3.0 \
    crate://crates.io/cc/1.0.79 \
    crate://crates.io/cfg-if/1.0.0 \
    crate://crates.io/cfg_aliases/0.1.1 \
    crate://crates.io/cgmath/0.18.0 \
    crate://crates.io/ciborium-io/0.2.1 \
    crate://crates.io/ciborium-ll/0.2.1 \
    crate://crates.io/ciborium/0.2.1 \
    crate://crates.io/clap/3.2.25 \
    crate://crates.io/clap/4.3.11 \
    crate://crates.io/clap_builder/4.3.11 \
    crate://crates.io/clap_derive/4.3.2 \
    crate://crates.io/clap_lex/0.2.4 \
    crate://crates.io/clap_lex/0.5.0 \
    crate://crates.io/color_quant/1.1.0 \
    crate://crates.io/colorchoice/1.0.0 \
    crate://crates.io/core-foundation-sys/0.8.4 \
    crate://crates.io/core-foundation/0.9.3 \
    crate://crates.io/core-graphics-types/0.1.2 \
    crate://crates.io/core-graphics/0.22.3 \
    crate://crates.io/crc32fast/1.3.2 \
    crate://crates.io/criterion-plot/0.5.0 \
    crate://crates.io/criterion/0.4.0 \
    crate://crates.io/crossbeam-channel/0.5.8 \
    crate://crates.io/crossbeam-deque/0.8.3 \
    crate://crates.io/crossbeam-epoch/0.9.15 \
    crate://crates.io/crossbeam-utils/0.8.16 \
    crate://crates.io/crunchy/0.2.2 \
    crate://crates.io/dispatch/0.2.0 \
    crate://crates.io/dlib/0.5.2 \
    crate://crates.io/downcast-rs/1.2.0 \
    crate://crates.io/drm-ffi/0.5.0 \
    crate://crates.io/drm-fourcc/2.2.0 \
    crate://crates.io/drm-sys/0.4.0 \
    crate://crates.io/drm/0.9.0 \
    crate://crates.io/edid-rs/0.1.0 \
    crate://crates.io/either/1.8.1 \
    crate://crates.io/encoding-index-japanese/1.20141219.5 \
    crate://crates.io/encoding-index-korean/1.20141219.5 \
    crate://crates.io/encoding-index-simpchinese/1.20141219.5 \
    crate://crates.io/encoding-index-singlebyte/1.20141219.5 \
    crate://crates.io/encoding-index-tradchinese/1.20141219.5 \
    crate://crates.io/encoding/0.2.33 \
    crate://crates.io/encoding_index_tests/0.1.4 \
    crate://crates.io/equivalent/1.0.1 \
    crate://crates.io/errno-dragonfly/0.1.2 \
    crate://crates.io/errno/0.2.8 \
    crate://crates.io/errno/0.3.1 \
    crate://crates.io/exr/1.7.0 \
    crate://crates.io/fastrand/1.9.0 \
    crate://crates.io/fdeflate/0.3.0 \
    crate://crates.io/flate2/1.0.26 \
    crate://crates.io/float-cmp/0.9.0 \
    crate://crates.io/flume/0.10.14 \
    crate://crates.io/foreign-types-shared/0.1.1 \
    crate://crates.io/foreign-types/0.3.2 \
    crate://crates.io/fps_ticker/1.0.0 \
    crate://crates.io/futures-core/0.3.28 \
    crate://crates.io/futures-sink/0.3.28 \
    crate://crates.io/gbm-sys/0.2.2 \
    crate://crates.io/gbm/0.12.0 \
    crate://crates.io/generator/0.7.5 \
    crate://crates.io/gethostname/0.2.3 \
    crate://crates.io/getrandom/0.2.10 \
    crate://crates.io/gif/0.12.0 \
    crate://crates.io/gl_generator/0.14.0 \
    crate://crates.io/glow/0.12.2 \
    crate://crates.io/half/1.8.2 \
    crate://crates.io/half/2.2.1 \
    crate://crates.io/hashbrown/0.12.3 \
    crate://crates.io/hashbrown/0.14.0 \
    crate://crates.io/heck/0.4.1 \
    crate://crates.io/hermit-abi/0.1.19 \
    crate://crates.io/hermit-abi/0.3.2 \
    crate://crates.io/image/0.24.6 \
    crate://crates.io/indexmap/1.9.3 \
    crate://crates.io/indexmap/2.0.0 \
    crate://crates.io/input-sys/1.17.0 \
    crate://crates.io/input/0.8.3 \
    crate://crates.io/instant/0.1.12 \
    crate://crates.io/io-lifetimes/1.0.11 \
    crate://crates.io/is-terminal/0.4.9 \
    crate://crates.io/itertools/0.10.5 \
    crate://crates.io/itoa/1.0.8 \
    crate://crates.io/jni-sys/0.3.0 \
    crate://crates.io/jobserver/0.1.26 \
    crate://crates.io/jpeg-decoder/0.3.0 \
    crate://crates.io/js-sys/0.3.64 \
    crate://crates.io/khronos_api/3.1.0 \
    crate://crates.io/lazy_static/1.4.0 \
    crate://crates.io/lebe/0.5.2 \
    crate://crates.io/libc/0.2.147 \
    crate://crates.io/libloading/0.7.4 \
    crate://crates.io/libloading/0.8.0 \
    crate://crates.io/libseat-sys/0.1.7 \
    crate://crates.io/libseat/0.1.7 \
    crate://crates.io/libudev-sys/0.1.4 \
    crate://crates.io/linux-raw-sys/0.3.8 \
    crate://crates.io/linux-raw-sys/0.4.3 \
    crate://crates.io/lock_api/0.4.10 \
    crate://crates.io/log/0.4.19 \
    crate://crates.io/loom/0.5.6 \
    crate://crates.io/matchers/0.1.0 \
    crate://crates.io/memchr/2.5.0 \
    crate://crates.io/memmap2/0.5.10 \
    crate://crates.io/memoffset/0.6.5 \
    crate://crates.io/memoffset/0.7.1 \
    crate://crates.io/memoffset/0.9.0 \
    crate://crates.io/minimal-lexical/0.2.1 \
    crate://crates.io/miniz_oxide/0.7.1 \
    crate://crates.io/mio/0.8.8 \
    crate://crates.io/nanorand/0.7.0 \
    crate://crates.io/ndk-context/0.1.1 \
    crate://crates.io/ndk-sys/0.4.1+23.1.7779620 \
    crate://crates.io/ndk/0.7.0 \
    crate://crates.io/nix/0.24.3 \
    crate://crates.io/nix/0.25.1 \
    crate://crates.io/nix/0.26.2 \
    crate://crates.io/nom/7.1.3 \
    crate://crates.io/nu-ansi-term/0.46.0 \
    crate://crates.io/num-integer/0.1.45 \
    crate://crates.io/num-rational/0.4.1 \
    crate://crates.io/num-traits/0.2.15 \
    crate://crates.io/num_cpus/1.16.0 \
    crate://crates.io/num_enum/0.5.11 \
    crate://crates.io/num_enum/0.6.1 \
    crate://crates.io/num_enum_derive/0.5.11 \
    crate://crates.io/num_enum_derive/0.6.1 \
    crate://crates.io/objc-sys/0.2.0-beta.2 \
    crate://crates.io/objc2-encode/2.0.0-pre.2 \
    crate://crates.io/objc2/0.3.0-beta.3.patch-leaks.3 \
    crate://crates.io/once_cell/1.18.0 \
    crate://crates.io/oorandom/11.1.3 \
    crate://crates.io/orbclient/0.3.45 \
    crate://crates.io/os_str_bytes/6.5.1 \
    crate://crates.io/overload/0.1.1 \
    crate://crates.io/parking_lot/0.11.2 \
    crate://crates.io/parking_lot_core/0.8.6 \
    crate://crates.io/percent-encoding/2.3.0 \
    crate://crates.io/pin-project-internal/1.1.2 \
    crate://crates.io/pin-project-lite/0.2.10 \
    crate://crates.io/pin-project/1.1.2 \
    crate://crates.io/pin-utils/0.1.0 \
    crate://crates.io/pkg-config/0.3.27 \
    crate://crates.io/plotters-backend/0.3.5 \
    crate://crates.io/plotters-svg/0.3.5 \
    crate://crates.io/plotters/0.3.5 \
    crate://crates.io/png/0.17.9 \
    crate://crates.io/ppv-lite86/0.2.17 \
    crate://crates.io/proc-macro-crate/1.3.1 \
    crate://crates.io/proc-macro2/1.0.64 \
    crate://crates.io/profiling-procmacros/1.0.8 \
    crate://crates.io/profiling/1.0.8 \
    crate://crates.io/puffin/0.12.1 \
    crate://crates.io/puffin_http/0.9.0 \
    crate://crates.io/qoi/0.4.1 \
    crate://crates.io/quick-xml/0.28.2 \
    crate://crates.io/quote/1.0.29 \
    crate://crates.io/rand/0.8.5 \
    crate://crates.io/rand_chacha/0.3.1 \
    crate://crates.io/rand_core/0.6.4 \
    crate://crates.io/raw-window-handle/0.5.2 \
    crate://crates.io/rayon-core/1.11.0 \
    crate://crates.io/rayon/1.7.0 \
    crate://crates.io/redox_syscall/0.2.16 \
    crate://crates.io/redox_syscall/0.3.5 \
    crate://crates.io/regex-automata/0.1.10 \
    crate://crates.io/regex-automata/0.3.3 \
    crate://crates.io/regex-syntax/0.6.29 \
    crate://crates.io/regex-syntax/0.7.4 \
    crate://crates.io/regex/1.9.1 \
    crate://crates.io/renderdoc-sys/1.0.0 \
    crate://crates.io/renderdoc/0.11.0 \
    crate://crates.io/rustix/0.37.23 \
    crate://crates.io/rustix/0.38.4 \
    crate://crates.io/rustversion/1.0.13 \
    crate://crates.io/ryu/1.0.14 \
    crate://crates.io/same-file/1.0.6 \
    crate://crates.io/scan_fmt/0.2.6 \
    crate://crates.io/scoped-tls/1.0.1 \
    crate://crates.io/scopeguard/1.1.0 \
    crate://crates.io/serde/1.0.171 \
    crate://crates.io/serde_derive/1.0.171 \
    crate://crates.io/serde_json/1.0.102 \
    crate://crates.io/sharded-slab/0.1.4 \
    crate://crates.io/simd-adler32/0.3.5 \
    crate://crates.io/slog/2.7.0 \
    crate://crates.io/slotmap/1.0.6 \
    crate://crates.io/smallvec/1.11.0 \
    crate://crates.io/smithay-client-toolkit/0.16.0 \
    crate://crates.io/spin/0.9.8 \
    crate://crates.io/static_assertions/1.1.0 \
    crate://crates.io/strsim/0.10.0 \
    crate://crates.io/syn/1.0.109 \
    crate://crates.io/syn/2.0.25 \
    crate://crates.io/tempfile/3.6.0 \
    crate://crates.io/textwrap/0.16.0 \
    crate://crates.io/thiserror-impl/1.0.43 \
    crate://crates.io/thiserror/1.0.43 \
    crate://crates.io/thread_local/1.1.7 \
    crate://crates.io/tiff/0.8.1 \
    crate://crates.io/tinytemplate/1.2.1 \
    crate://crates.io/toml_datetime/0.6.3 \
    crate://crates.io/toml_edit/0.19.12 \
    crate://crates.io/tracing-attributes/0.1.26 \
    crate://crates.io/tracing-core/0.1.31 \
    crate://crates.io/tracing-log/0.1.3 \
    crate://crates.io/tracing-subscriber/0.3.17 \
    crate://crates.io/tracing/0.1.37 \
    crate://crates.io/tracy-client-sys/0.21.0 \
    crate://crates.io/tracy-client/0.15.2 \
    crate://crates.io/udev/0.7.0 \
    crate://crates.io/unicode-ident/1.0.10 \
    crate://crates.io/utf8parse/0.2.1 \
    crate://crates.io/valuable/0.1.0 \
    crate://crates.io/vec_map/0.8.2 \
    crate://crates.io/version_check/0.9.4 \
    crate://crates.io/walkdir/2.3.3 \
    crate://crates.io/wasi/0.11.0+wasi-snapshot-preview1 \
    crate://crates.io/wasm-bindgen-backend/0.2.87 \
    crate://crates.io/wasm-bindgen-macro-support/0.2.87 \
    crate://crates.io/wasm-bindgen-macro/0.2.87 \
    crate://crates.io/wasm-bindgen-shared/0.2.87 \
    crate://crates.io/wasm-bindgen/0.2.87 \
    crate://crates.io/wayland-backend/0.1.2 \
    crate://crates.io/wayland-client/0.29.5 \
    crate://crates.io/wayland-commons/0.29.5 \
    crate://crates.io/wayland-cursor/0.29.5 \
    crate://crates.io/wayland-egl/0.30.0 \
    crate://crates.io/wayland-protocols-misc/0.1.0 \
    crate://crates.io/wayland-protocols-wlr/0.1.0 \
    crate://crates.io/wayland-protocols/0.29.5 \
    crate://crates.io/wayland-protocols/0.30.1 \
    crate://crates.io/wayland-scanner/0.29.5 \
    crate://crates.io/wayland-scanner/0.30.1 \
    crate://crates.io/wayland-server/0.30.1 \
    crate://crates.io/wayland-sys/0.29.5 \
    crate://crates.io/wayland-sys/0.30.1 \
    crate://crates.io/web-sys/0.3.64 \
    crate://crates.io/weezl/0.1.7 \
    crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi-util/0.1.5 \
    crate://crates.io/winapi-wsapoll/0.1.1 \
    crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi/0.3.9 \
    crate://crates.io/windows-sys/0.45.0 \
    crate://crates.io/windows-sys/0.48.0 \
    crate://crates.io/windows-targets/0.42.2 \
    crate://crates.io/windows-targets/0.48.1 \
    crate://crates.io/windows/0.48.0 \
    crate://crates.io/windows_aarch64_gnullvm/0.42.2 \
    crate://crates.io/windows_aarch64_gnullvm/0.48.0 \
    crate://crates.io/windows_aarch64_msvc/0.42.2 \
    crate://crates.io/windows_aarch64_msvc/0.48.0 \
    crate://crates.io/windows_i686_gnu/0.42.2 \
    crate://crates.io/windows_i686_gnu/0.48.0 \
    crate://crates.io/windows_i686_msvc/0.42.2 \
    crate://crates.io/windows_i686_msvc/0.48.0 \
    crate://crates.io/windows_x86_64_gnu/0.42.2 \
    crate://crates.io/windows_x86_64_gnu/0.48.0 \
    crate://crates.io/windows_x86_64_gnullvm/0.42.2 \
    crate://crates.io/windows_x86_64_gnullvm/0.48.0 \
    crate://crates.io/windows_x86_64_msvc/0.42.2 \
    crate://crates.io/windows_x86_64_msvc/0.48.0 \
    crate://crates.io/winit/0.28.6 \
    crate://crates.io/winnow/0.4.9 \
    crate://crates.io/wio/0.2.2 \
    crate://crates.io/x11-dl/2.21.0 \
    crate://crates.io/x11rb-protocol/0.11.1 \
    crate://crates.io/x11rb/0.11.1 \
    crate://crates.io/xcursor/0.3.4 \
    crate://crates.io/xkbcommon/0.5.0 \
    crate://crates.io/xml-rs/0.8.15 \
    crate://crates.io/zstd-safe/4.1.3+zstd.1.5.1 \
    crate://crates.io/zstd-sys/1.6.2+zstd.1.5.1 \
    crate://crates.io/zstd/0.9.2+zstd.1.5.1 \
    crate://crates.io/zune-inflate/0.2.54 \
"

DEPENDS:append = " wayland wayland-utils systemd seatd pkgconfig udev virtual/libgbm libgudev mesa libinput libxkbcommon"

# FIXME: update generateme with the real MD5 of the license file
LIC_FILES_CHKSUM = " \
    file://MIT;md5=generateme \
"

SUMMARY = "anvil"
HOMEPAGE = "https://github.com/dhruveshb-mecha/smithay"
LICENSE = "MIT"

# includes this file if it exists but does not fail
# this is useful for anything you may want to override from
# what cargo-bitbake generates.
include anvil-${PV}.inc
include anvil.inc
