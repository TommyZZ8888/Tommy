<table>
	<tr style="height: 50px;">
		<td style="font-size: 1.8em;"><strong>张卫刚</strong></td>
		<td style="font-size: 1.5em;"><strong>求职意向：java开发工程师</strong></td>
		<td rowspan="5">
			<img src="./image/sticki_avatar.jpg" height="140" alt="">
		</td>
	</tr>
	<tr>
		<td>工作经验：五年</td>
		<td>电话：15255926173</td>
	</tr>
	<tr>
		<td>出生年月：1997年11月</td>
		<td>邮箱：1950442495@qq.com</td>
	</tr>
	<tr>
		<td>现住址：江苏南京</td>
		<td>教育背景：2016.09-2020.06 安徽信息工程学院 本科</td>
	</tr>
</table>
<hr/>

### ==专业技能==

- 熟练掌握**Spring，Springboot，Mybatis，Mybatis-plus**等开源框架技术；熟悉 **SpringCloud** 常用组件
- 熟练掌握 **MySQL** 数据库，熟悉 **SQL优化** 和 **索引结构** ，熟悉底层 B+树 的结构，熟悉其相关原理
- 熟悉 **Redis** 非关系型数据库，熟悉缓存、持久化、分布式锁，熟悉其相关原理
- 熟悉消息队列**kafka**的使用，熟悉数据处理引擎**Flink**的使用，了解**rocketmq**的使用
- 熟悉**jvm**相关原理，熟练掌握**线程池**技术，多线程，**JUC**常用工具类的使用
- 熟悉任务调度框架**Quartz**的使用，熟悉对象存储**MinIO**的使用
- 熟悉**Docker**容器的使用，熟悉**Linux**常用命令，熟悉服务器**nginx**的使用
- 熟练使用版本管理工具**git，svn**；熟悉搜索引擎**RediSearch**
- 熟悉**设计模式**，有在工作中使用来提高程序可扩展性和可维护性
- 熟悉**vue3**的使用，熟悉**typeScript/javaScript**的使用

<hr/>

### ==工作经历==

<h4 style="display: flex;justify-content: space-between;">
<span>一、君度科技有限公司</span><span>java开发工程师</span><span>2024.08 - 至今</span>
</h4>
- 负责开发纪委监委大数据监督平台，完成了数据录入，比对分析，预警等功能，支撑了政务业务的发展，提高了预警立案转化率

<h4 style="display: flex;justify-content: space-between;">
<span>二、南京曜石软件技术有限公司</span><span>java后端开发工程师</span><span>2022.06 - 2024.05</span>
</h4>
- 主要负责开发焊接管控平台，实现了从排产派工到工作数据统计的全流程自动化管理，包括人员和设备的工作信息统计、产品生产工序的信息追溯、以及物资信息管理和质量检测等环节，同时优化了项目管理和库存操作，从而简化了纸质操作流程，提高了生产和管理效率

<h4 style="display: flex;justify-content: space-between;">
<span>三、凌臣科技有限技术公司</span><span>后端开发工程师</span><span>2020.07 - 2022.03</span>
</h4>

- 主要负责生产管控平台的开发与维护，业务流程处理，功能迭代、组件库优化与技术文档编写，保障系统的高效运行与持续升级。

<hr/>

### ==工作项目==

<h4 style="display: flex;justify-content: space-between;">
<span>一、消息推送平台</span><span>主力开发</span><span></span>
</h4>
项目背景及描述：

- 消息消息推送平台承接着站内对各种渠道类型消息的下发。主要用于用户的工作通知，维保通知以及客户营销等场景。

项目技术栈：springboot，springcloud，mybatisPlus，redis，kafka，flink，quartz

工作内容/个人职责：

- 消息资源隔离，高性能消费：通过设计多通道消息隔离机制，确保不同渠道、不同类型的消息处理相互独立、互不干扰；结合线程池技术优化消费流程，提升并发处理能力与系统吞吐量，保障消息高效稳定消费。

- 全渠道类型的消息生命周期链路追踪：在消息处理的关键节点进行埋点，采集完整的流转路径信息，统一发送至 Kafka；通过 Flink 实时清洗、聚合与处理后写入 Redis，实现消息全链路可追踪、状态可查询，便于问题定位与监控分析。

- 采用责任链设计模式对消息发送前的校验、组合、转换等操作进行解耦式处理，支持灵活扩展处理逻辑；在下发前执行去重、屏蔽等关键操作，确保消息的准确性与合规性。

- 消息去重：使用redis + lua 实现滑动窗口限流，对消息频次和内容进行限流去重

  

<h4 style="display: flex;justify-content: space-between;">
<span>二、纪委监委公权力大数据监督平台</span><span>协作开发</span><span></span>
</h4>

项目描述：

- 负责全市各区县机关、各镇、各企事业单位相关信息的全面收集与录入工作，构建了一个集数据采集、存储、分析于一体的综合信息管理系统。通过多维度、多层次的数据比对策略，结合先进的数据分析技术，旨在发现并预防弄虚作假、敷衍塞责以及微腐败等问题，强化政治监督机制，确保公共管理的透明度和公正性。

项目技术栈：springboot，mybatisPlus，redis，quartz， minio，easypoi

工作内容/个人职责：

- 重构优化策略比对模块：采用 CompletableFuture 与 MethodHandle 重构优化了比对策略的执行，实现了动态策略加载、异步并发处理及灵活触发模式，并支持基于模块Key的批量执行和基于策略Key的单策略执行，以适应多样化的业务需求。

- 引入 RediSearch将高频查询的业务数据（如任务信息、人员信息、设备状态等）同步至 Redis Hash 结构，利用 RediSearch 建立全文索引，并结合中文分词策略，实现了对业务关键词的快速匹配与精准定位

- 利用 AntX V6 框架实现比对模型流程的可视化配置，支持用户通过拖拽节点（数据表、分组、关联等）构建数据处理流程。系统动态解析各节点配置（字段映射、聚合规则等），并通过拓扑排序算法自动解决节点间的依赖关系，智拼接生成高效的 SQL 语句，提升系统的灵活性与扩展性。

  

<h4 style="display: flex;justify-content: space-between;">
<span>二、中建五洲焊接管控平台</span><span>协作开发</span><span></span>
</h4>

项目背景及描述：

- 焊接数字化管控平台实现了排产派工、任务执行、质量检测、物资管理及库存操作的全流程数字化。系统根据规则自动调度任务，支持在线领工报工，实时统计人员与设备数据，实现产品生产全过程信息追溯。平台涵盖物资计划、采购、合同、订单等业务管理功能，集成入库、领料、退库等库存操作，提升仓储效率。项目管理模块展示基本信息与产量产值，并通过大屏可视化呈现项目分布。

项目技术栈：springboot，springcloud，mybatisPlus，redis，easyexcel，easypoi，sse，websocket

工作内容/个人职责：

- 针对车间流程复杂、多变以及不同物资类型的处理流程不同的特点，采用策略模式实现不同工序任务，不同物资类型的处理动态切换与扩展，结合模板方法模式统一各工序执行流程，提升代码可维护性与复用性，降低业务逻辑耦合度。
- 通过多线程并发处理与分片批量插入优化 Excel 数据导入性能，结合 `ConcurrentHashMap` 缓存共享数据及双重校验锁机制，高效完成数据的动态解析、关联信息处理及数据库插入操作，显著提升大数据量场景下的导入效率与系统响应能力。
- 基于反射与Lambda表达式实现通用Stream过滤器，动态构建字段条件筛选逻辑，简化复杂数据过滤操作，提升代码复用性与开发效率。
- 通过 Server-Sent Events 协议，建立服务端到前端的实时通信通道，用于推送生产进度、项目完成情况等关键指标，支撑大屏数据的实时刷新与展示。
- 集成 FFmpeg 对工业摄像头的 RTSP 视频流进行解析与转码，再通过 WebSocket 将视频帧推送到前端，实现浏览器端无插件实时播放监控画面。
- 使用redis分布式锁实现接口幂等
-  利用easyexcel和poi实现各种报表的导入导出

<hr/>

### ==自我评价==

- 酷爱编程，严格要求自己写出规范负责的代码，对技术有强烈的兴趣。
- 独立解决问题的能力，能承受一定的工作压力
- 具有强烈的自我驱动力，具有良好的新知识接受能力。

<hr/>



<style>
    #write {
        padding: 25px 25px 0px;
    }
    hr {
        margin: 6px;
    }
    li {
        margin: 4px;
    }
    p {
        margin: 4px 13px;
    }
    li p{
        margin: 5px 0;
    }
    h1 {
        margin: 8px 15px;
    }
    h3 {
        margin: 9px;
    }
    h4 {
        margin: 7px;
    }
    figure {
        margin: 7px 0px;
    }
    blockquote {
        padding-left: 16px;
    }
    /* 链接下划线 */
    a {
        text-decoration:underline;
    }
    /* 图片阴影效果 */
    img {
        box-shadow: 0px 0px 10px rgba(0,0,0,.5);
    }
    /* 表格样式，去除边框显示 */
    table, table td, table tr, table th, th {
        font-weight: normal;
        padding: 3px 13px;
        border: 0px;
        background-color: #ffffff;
    }
</style>