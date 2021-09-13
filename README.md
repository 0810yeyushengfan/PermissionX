# PermissionX  

PermissionX是一个用于简化Android运行时权限用法的开源库

目前由于jcenter仓库停止更新，bintray被弃用，所以还未发布  

可以使用如下语法来申请运行时权限:
```kotlin
PermissionX.request(this,Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS){allGranted,deniedList->
                if(allGranted){
                    Toast.makeText(this,"All permissions are granted",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                }
            }
            ```
            
           
