# FilterPopWindow
实现数据筛选并记忆选项功能的popwindow
## 效果图  
![项目效果](https://github.com/hanjie511/FilterPopWindow/blob/main/MyVideo_4.gif)  
## 在项目中引用
### 通过Gradle添加依赖  
```java  
//Add it in your root build.gradle at the end of repositories:  
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
  }
 //Add the dependency
 dependencies {
  implementation 'com.github.hanjie511:FilterPopWindow:v1.0.0'
  }  
```  
### 通过Maven添加依赖  
```java  
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>  
<dependency>
  <groupId>com.github.hanjie511</groupId>
    <artifactId>FilterPopWindow</artifactId>
    <version>Tag</version>
</dependency>  
```  
## 在项目中调用  
* Step1:初始化HJFilterPopWindow对象  
```java  
HJFilterPopWindow hj=new HJFilterPopWindow(Context context);  
```  
* Step2:设置数据源和要显示的parentView  
```java  
List<Map<String,Object>> list=new ArrayList<>();
Map<String,Object> m1=new HashMap<>();
m1.put(HJFilterPopWindow.isShowMore,"false");//设置该属性为true时，筛选的项会全部展开并显示，为false时，筛选的项最多只显示4项。
m1.put(HJFilterPopWindow.tagName,"姓名");//设置筛选的标签名称
List<MyFilter> list1=new ArrayList<>();
MyFilter myFilter=null;
for(int i=0;i<10;i++){
  myFilter=new MyFilter();
  myFilter.setName("张三"+i);
  myFilter.setSelected(false);
  list1.add(myFilter);
  }
list.add(m1);
m1.put(HJFilterPopWindow.dataSource,list1);//设置筛选的数据源
// hj.setDataSource(List<Map<String,Object>>);
hj.setDataSource(list);
//显示的parentView
LinearLayout mainLinear=findViewById(R.id.mainLinear);
hj.showAtLocation(mainLinear, Gravity.RIGHT,0,0);
```  
* Step3:设置数据返回的监听(点击确定和取消都会触发)  
```java  
hj.setHJFilterPopwindowDismisListener(new HJFilterPopWindow.HJFilterPopwindowDismisListener() {
@Override
public void getFilterResult(List<Map<String, Object>> list1, Map m) {
// list1筛选后数据源
//m筛选的结果
//获取选择结果的sample:
String str="姓名："+m.get("姓名")+"\n";
str=str+"颜色："+m.get("颜色")+"\n";
str=str+"品牌："+m.get("品牌")+"\n";
AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
builder.setTitle("你选择的内容为：");
builder.setMessage(str);
builder.create().show();
  }
  });  
```  
## 初期版本，后续功能将会持续优化.....
