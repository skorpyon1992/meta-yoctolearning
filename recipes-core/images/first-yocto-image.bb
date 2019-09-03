include recipes-core/images/core-image-minimal.bb

DEPENDS += "barebox barebox-ipl"

IMAGE_INSTALL_append = "first-yocto-recipe testing-script second-yocto-recipe-script kernel-image-zimage"
