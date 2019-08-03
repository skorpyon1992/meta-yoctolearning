include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append = "first-yocto-recipe testing-script second-yocto-recipe-script"
