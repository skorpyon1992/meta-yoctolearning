inherit barebox-environment-2

python do_env_append() {

    mount_args1 = """
mount /dev/mmc0.1 /mnt/rootfs1
"""
    boot_args1 = """
global.linux.bootargs.dyn.root="root=/dev/mmcblk0p2 rootflags='data=journal'"
"""
    oftree_name = d.getVar('KERNEL_DEVICETREE')

    env_add(d, "boot/system1",
"""#!/bin/sh

[ -e /env/config-expansions ] && /env/config-expansions
mkdir -p /mnt/rootfs1
""" + mount_args1 + """
global.bootm.image=/mnt/rootfs1/boot/zImage

let bootchooser.system1.remaining_attempts-=1

global.bootm.oftree=/mnt/rootfs1/boot/""" + oftree_name + """
"""+ boot_args1 + """
""")
    env_add(d, "nv/boot,watchdog_timeout", "60\n")
    env_add(d, "nv/boot.default", "bootchooser recovery\n")
    env_add(d, "nv/bootchooser.retry", "true\n")
    env_add(d, "nv/bootchooser.targets", "system1\n")
    env_add(d, "init/bootchooservalue",
"""#!/bin/sh

echo "Setting bootchooser value"

nv bootchooser.system1.boot=system1
nv bootchooser.system1.default_attempts=3
nv bootchooser.system1.remaining_attempts=3
nv bootchooser.system1.default_priority=10
nv bootchooser.system1.priority=10

nv boot.default="bootchooser recovery"

""")

}

