学习笔记
 javac -encoding gbk GCLogAnalysis.java
#  使用串行GC
## 串行512m
java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:SerialGC.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
19次GC，两次Full GC 对象10873

## 串行1g
java -XX:+UseSerialGC -Xms1g -Xmx1g -Xloggc:SerialGC-1g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
13次GC，没有Full GC 对象14737
性能好

## 串行4g
java -XX:+UseSerialGC -Xms4g -Xmx4g -Xloggc:SerialGC-4g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
3次GC，没有Full GC 对象13198

# 使用并行GC
## 并行512m
java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:ParallelGC-512m.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps 
43次GC，12次Full GC 对象9526

## 并行1g
java -XX:+UseParallelGC -Xms1g -Xmx1g -Xloggc:ParallelGC-1g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps 

## 并行4g
java -XX:+UseParallelGC -Xms4g -Xmx4g -Xloggc:ParallelGC-4g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps 

#使用CMS GC
## CMS GC 512m
java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:CMS-512m.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
47次GC，CMS标记89次，没有Full GC 对象11716

## CMS GC 1g
java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -Xloggc:CMS-1g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
27次GC，CMS标记22次，没有Full GC 对象16935

## CMS GC 4g
java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xloggc:CMS-4g.log  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
4次GC，CMS标记0次，没有Full GC 对象15082

# 使用G1 GC
## G1 GC 512m
java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:G13-512m.log  -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
323次GC，4次Full GC 对象11342 执行卡死

## G1 GC 1g
java -XX:+UseG1GC -Xms1g -Xmx1g -Xloggc:G13-1g.log  -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
107次GC，没有Full GC 对象16958

## G1 GC 4g
java -XX:+UseG1GC -Xms4g -Xmx4g -Xloggc:G13-4g.log  -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
19次GC，没有Full GC 对象16202

# 总结
总体来说，G1 GC性能是最不错的
