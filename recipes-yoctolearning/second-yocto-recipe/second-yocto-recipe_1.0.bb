SUMMARY = "The second recipe with two packages"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
LICENSE = "MIT"

S = "${WORKDIR}"
SRC_URI = "file://second-yocto-script.sh \
           file://testing-script.sh \
           file://copying.txt"
LIC_FILES_CHKSUM = "file://copying.txt;md5=157d0ad2c2ec995ea065d866091a5622"

# Packages
PACKAGE_BEFORE_PN += " ${PN}-script testing-script"
FILES_${PN}-script = "${sbindir}/second-yocto-script.sh"
FILES_testing-script = "${sbindir}/testing-script.sh"

do_install() {
    install -d ${D}${sbindir}
    install -m 775 ${S}/second-yocto-script.sh ${D}${sbindir}
    install -m 775 ${S}/testing-script.sh ${D}${sbindir}
}
