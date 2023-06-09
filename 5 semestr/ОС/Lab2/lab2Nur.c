#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/pci.h>
#include <linux/debugfs.h>
#include <linux/fs.h>
#include <linux/seq_file.h>
#include <linux/pid.h>
#include <linux/mm.h>
#include <linux/mm_types.h>
#include <linux/sched.h>
#include <linux/types.h>

#define BUFFER_SIZE 128

MODULE_LICENSE("Dual BSD/GPL");
MODULE_AUTHOR("Nurullaev_Daniil");
MODULE_DESCRIPTION("Laba2OS");
MODULE_VERSION("1.0");

static struct dentry *kmod_dir;
static struct dentry *args_file;
static struct pci_dev *pci_device = NULL;
static struct task_struct* task = NULL;
static void print_device(struct seq_file *file, struct pci_dev *pci_device);
static void print_vm_area_struct(struct seq_file *file, struct task_struct *task);
static int print_struct(struct seq_file *file, void *data);


static ssize_t write_structures(struct file *file, const char __user *buffer, size_t length, loff_t *ptr_offset) {
printk(KERN_INFO "get arguments\n");
char user_data[BUFFER_SIZE];
int pid;
unsigned int vendor_id, device_id;
copy_from_user(user_data, buffer, length);
sscanf(user_data, "pid: %d, vendor_id: %x, device_id: %x", &pid, &vendor_id, &device_id);
pci_device = pci_get_device(vendor_id, device_id, NULL);
task = get_pid_task(find_get_pid(pid),PIDTYPE_PID);
printk(KERN_INFO "get arguments\n");
single_open(file, print_struct, NULL);
return strlen(user_data);
}

static struct file_operations fops = {
        .read = seq_read,
        .write = write_structures,
};


static int __init mod_init(void) {
    printk(KERN_INFO "module loading.\n");
    kmod_dir = debugfs_create_dir("lab2", NULL);
    args_file = debugfs_create_file("structs", 0644, kmod_dir, NULL, &fops);
    return 0;
}

static void __exit mod_exit(void) {
    debugfs_remove_recursive(kmod_dir);
    printk(KERN_INFO "module unloaded\n");
}


static int print_struct(struct seq_file *file, void *data) {
    print_device(file, pci_device);
    print_vm_area_struct(file, task);
    return 0;
}

static void print_vm_area_struct(struct seq_file *file, struct task_struct *task) {
    if (task == NULL) {
        seq_printf(file, "Can't find vm_area_struct with this pid\n");
    } else  {
        if (task->mm ==0){
            seq_printf(file, "Can't find vm_area_Struct with this pid\n");
        }else{
            seq_printf(file, "vm area struct\n");
            struct vm_area_struct *pos = NULL;
            for(pos = task->mm->mmap;pos;pos = pos->vm_next){
                seq_printf(file,"0x%hx-0x%hx\t",pos->vm_start,pos->vm_end);
                if (pos->vm_flags & VM_READ){
                    seq_printf(file,"r");
                }else{
                    seq_printf(file,"-");
                }
                if (pos->vm_flags & VM_WRITE){
                    seq_printf(file,"w");
                }else{
                    seq_printf(file,"-");
                }
                if (pos->vm_flags & VM_EXEC){
                    seq_printf(file,"x");
                }else{
                    seq_printf(file,"-");
                }
                seq_printf(file,"\t%1d",pos->rb_subtree_gap);
                seq_printf(file,"\n");
            }
            // seq_printf(file, "vm_start: %x,\n", task->mm->mmap->vm_start);
        }
    }
}

static void print_device(struct seq_file *file, struct pci_dev *pci_device) {
    if (pci_device == NULL) {
        seq_printf(file, "Can't find pci_device with this params\n");
    } else {
        seq_printf(file, "pci_device structure: {\n");
        seq_printf(file, "  vendor_ID: %u,\n", pci_device->vendor);
        seq_printf(file, "  device_ID: %u,\n", pci_device->device);
        seq_printf(file, "  function index: %u,\n", pci_device->devfn);
        seq_printf(file, "  interrupt pin: %u,\n", pci_device->pin);
        seq_printf(file, "  class: %u,\n", pci_device->class);
        seq_printf(file, "  revision: %u\n", pci_device->revision);
        seq_printf(file, "}\n");
    }
}


module_init(mod_init);
module_exit(mod_exit);
