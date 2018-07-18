[ ![Download](https://api.bintray.com/packages/wenchieh/maven/shadowLayout/images/download.svg?version=0.8.0) ](https://bintray.com/wenchieh/maven/shadowLayout/0.8.0/link)


原先使用阴影都在 http://inloop.github.io/shadow4android/ 生成一个 .9 图，但是这个网站生成的阴影局限性比较大，矩形的四边阴影宽度只能一样，阴影的宽度是和阴影的模糊度关联的，模糊度越大，阴影的宽度也越大，没法自己控制宽度。

于是有了这个控件。

支持：
1. 自定义阴影的颜色
2. 自定义上下左右阴影的宽度
3. 自定义阴影的模糊度
4. 自定义阴影矩形的圆角


## 使用方法
```xml
    <me.wenchieh.shadowlayout.ShadowLayout
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:id="@+id/shadowLayout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:blur="20dp"
      app:radius="5dp"
      app:shadowBottom="10dp"
      app:shadowTop="20dp"
      app:shadowLeft="15dp"
      app:shadowRight="15dp"
      app:shadowColor="@color/colorPrimary"
      >
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorAccent"
        android:gravity="center"
        android:text="@string/app_name"
        />
  </me.wenchieh.shadowlayout.ShadowLayout>

```


## 效果图

![](http://7xt4re.com1.z0.glb.clouddn.com/20180718153188520047758.png)