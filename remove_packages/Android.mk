LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := RemovePackages
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_TAGS := optional
LOCAL_OVERRIDES_PACKAGES := AppDirectedSMSService Camera2 CarrierSetup ConnMO DCMO Drive Maps MyVerizonServices OBDM_Permissions OemDmTrigger PrebuiltGmail Snap SprintDM SprintHM USCCDM VZWAPNLib VzwOmaTrigger YouTube YouTubeMusicPrebuilt obdm_stub
LOCAL_UNINSTALLABLE_MODULE := true
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_SRC_FILES := /dev/null
include $(BUILD_PREBUILT)
