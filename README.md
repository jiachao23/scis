基于web的高校双创项目信息管理系统采用灵活的系统架构，通过实现高校双创项目的信息化管理，突破传统人工管理模式的限制，在一定程度上大大提高了高校双创项目的申报审批效率。同时，通过高校双创项目信息管理系统的实施,可以掌控高校双创项目申报、项目指导、审批整个流程的具体信息，实现信息高效流通，全面提升高校双创项目管理的效率和质量。


#### 项目部署

#### 本项目使用Springboot+Mysql+Flyway+Layui

>  *  安装jdk和mysql环境。注意mysql本系统使用的是最新版本。低版本可能会出现下面问题

>       Incorrect table definition :There can be only one TIMESTAMP columu.....

>       将脚本文件中涉及到TIMESTAMP的字段修改为如下样子，例如：
       
       >  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       >  `end` timestamp NOT NULL,
>  *  将代码clone下来后，用idea打开。</br>
>  *  修改数据库配置，帐号密码。
>  *  如果是首次运行，先在数据库里面创建一个数据库scis。
>  *  运行项目，程序会自动创建相关的表结构。
