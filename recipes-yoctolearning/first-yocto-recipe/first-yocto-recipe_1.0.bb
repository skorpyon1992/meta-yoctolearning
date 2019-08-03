inherit cmake
inherit update-rc.d

SUMMARY = "First Yocto Recipe with init script"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/skorpyon1992/yocto-learning.git;protocol=https \
           file://first-yocto-app.init"
SRC_URI[md5sum] = "a293470ac9faeb3c4ac074fa687b9503"
SRC_URI[sha256sum] = "6a04fa7eb0f453b807621ff239f0408c754e5ad70e37704b6c9866289ee08c28"
SRCREV = "master"
LIC_FILES_CHKSUM = "file://copying.txt;md5=157d0ad2c2ec995ea065d866091a5622"

DEPENDS = "boost"

# The packages which will contain an initscript
INITSCRIPT_PACKAGES += "${PN}"
INITSCRIPT_NAME = "first-yocto-app"
INITSCRIPT_PARAMS = "start 90 5 4 3 2 . stop 10 0 1 6 ."

do_configure() {
  cd ${S}
  cmake ./
}

do_compile() {
    cd ${S}
    make
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}/init.d
    install ${S}/bin/first-yocto-app ${D}${bindir}
    install -m 775 ${S}/../first-yocto-app.init ${D}${sysconfdir}/init.d/first-yocto-app
}
