﻿<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>双创信息</title>
    <link rel="shortcut icon" href="${ctx!}/images/logo.png" type="image/x-icon">
    <!--Layui-->
    <link href="${ctx!}/js/plugins/layui/css/layui.css" rel="stylesheet" />
    <!--font-awesome-->
    <link href="${ctx!}/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${ctx!}/css/global.css" rel="stylesheet" />
    <link href="${ctx!}/css/animate.min.css" rel="stylesheet" />
    <!-- 本页样式表 -->
    <link href="${ctx!}/css/index.css" rel="stylesheet" />
    <link href="${ctx!}/css/video-js.css" rel="stylesheet" />
</head>
<body>
    <!-- 导航 -->
    <nav class="blog-nav layui-header">
        <div class="blog-container">
            <!-- 用户登陆 -->
			<a class="blog-logo" href="/" style="top: -20px;">双创信息</a>

            <form class="layui-form" action="">
                <button class="layui-btn  layui-btn-primary" lay-filter="login" style="float: right">登录</button>
            <#--<div class="layui-form-item">-->
            <#--<div class="search-keywords  shadow">-->
            <#--<input type="text" name="keywords" lay-verify="required" placeholder="搜索" autocomplete="off" class="layui-input ">-->
            <#--</div>-->
            <#--<div class="search-submit  shadow">-->
            <#--<a class="search-btn" lay-submit="formSearch" lay-filter="formSearch"><i class="fa fa-search"></i></a>-->
            <#--</div>-->
            <#--</div>-->
            </form>

            <div class="blog-main">
                <div class="blog-main-right">
                <div class="blog-search">
                    <form class="layui-form" action="">
                        <div class="layui-form-item" style="margin-right: 30px">
                            <div class="search-keywords  shadow">
                                <input type="text" name="keywords" lay-verify="required" placeholder="搜索" autocomplete="off" class="layui-input ">
                            </div>
                            <div class="search-submit  shadow">
                                <a class="search-btn" lay-submit="formSearch" lay-filter="formSearch"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            </div>
            <!-- 导航菜单 -->



			<ul class="layui-nav" lay-filter="nav">

			</ul>
			<!-- 手机和平板的导航开关 -->
			<a class="blog-navicon" href="javascript:;">
				<i class="fa fa-navicon"></i>
			</a>
        </div>
    </nav>
    <!-- 主体（一般只改变这里的内容） -->
    <div class="blog-body">
    	<div class="layui-carousel blog-bg" id="carousel">
		  <div carousel-item>
              <@circularList>
                  <#list list as x>
                      <a href="${(x.url)!}" target="_blank"><div class="bg"><img src="${(x.book.downloadUrl)!}"></div></a>
                  </#list>
              </@circularList>
		    <div class="bg "></div>
		    <div class="bg "></div>
		    <div class="bg "></div>
		    <div class="bg "></div>
		    <div class="bg "></div>
		  </div>
		</div>
        <div class="blog-container">
            <div class="blog-main">
                <!-- 公告部分 start -->
                <#--<div class="home-tips shadow">-->
                    <#--<i style="float:left;line-height:17px;" class="fa fa-volume-up"></i>-->
                    <#--<div class="home-tips-container">-->
                    <#--<@circularList>-->
                        <#--<#list list as x>-->
                            <#--<#if x.url != null>-->
                                <#--<span style="color: red">${(x.content)!}<a href="${x.url}" target="_blank" style="color:#01AAED">&nbsp;&nbsp;点我前往</a></span>-->
                            <#--<#else >-->
                                <#--<span style="color: #009688">${(x.content)!}</span>-->
                            <#--</#if>-->
                        <#--</#list>-->
                    <#--</@circularList>-->
                    <#--</div>-->
                <#--</div>-->
                <!--左边文章列表-->
                <div class="blog-main-left animated slideInLeft">
					<@projectList>
						<#list list as x>
							<div class="article shadow animated fadeInLeft">

                                <div class="article-left ">
                                    <img src="${ctx!}/images/01.jpg" alt="${x.name}"/>
                                </div>
                                <div class="article-right">
                                    <div class="article-title">
                                        <a href="/jobSeeker/job/${x.id}">项目名称：${x.name}</a>
                                    </div>
                                    <div class="article-abstract">
                                        项目来源：${x.proResource}
                                    </div>
                                    <div class="article-abstract">
                                        经费来源：${x.moneyResource}
                                    </div>
                                    <div class="article-abstract">
                                        项目描述：${x.desc}
                                    </div>
                                    <div class="article-abstract">
                                        指导老师：${x.teacher.name}
                                    </div>
                                </div>
                                <div class="clear"></div>
                                <div class="article-footer">
                                    <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;${x.student.name}</span>
                                    <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;${x.createTime}</span>
                                    <!--<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>-->
                                    <!--<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>-->
                                </div>
                            </div>
						</#list>
					</@projectList>
                </div>
                <!--右边小栏目-->
                <div class="blog-main-right">
                    <@projectList>
                        <#list list as x>
							<div class="article shadow animated fadeInLeft">
                                <#if (x.video.downloadUrl) == null >
                                    <div class="video_div" style="width: 73%">
                                        <img src="${ctx!}/images/01.jpg" alt="${x.name}"/>
                                    </div>
                                    <div style="float: right;width: 20%">
                                        ${x.name}项目无参赛视频
                                    </div>
                                <#else >
                                    <div class="video_div">
                                        <video id="my-video" class="video-js vjs-default-skin" controls preload="none"  data-setup="{}" poster="http://vjs.zencdn.net/v/oceans.png">
                                            <source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">
                                        </video>
                                    </div>
                                    <div style="float: right;width: 20%">
                                        ${x.name}项目参赛视频
                                    </div>
                                </#if>
                            </div>
                        </#list>
                    </@projectList>

					<div class="blog-module shadow">
						<div class="blog-module-title">友情链接</div>
						<ul class="blogroll">
							<li><a target="_blank" href="http://www.layui.com/" title="Layui">Layui</a></li>
							<li><a target="_blank" href="http://www.pagemark.cn/" title="页签">页签</a></li>
						</ul>
					</div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!-- 底部 -->
	<footer class="blog-footer">
		<p><span>Copyright</span><span>&copy;</span><span>2018</span><a href="localhost">SCIS</a></p>
	</footer>
    <!--侧边导航-->
    <ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
		<#--<li class="layui-nav-item layui-this">-->
			<#--<a href="/"><i class="fa fa-home fa-fw"></i>&nbsp;招聘信息</a>-->
		<#--</li>-->

		<#--<li class="layui-nav-item">-->
			<#--<a href="/user/index"><i class="fa fa-tags fa-fw"></i>&nbsp;个人中心</a>-->
		<#--</li>-->
    </ul>

    <!--遮罩-->
    <div class="blog-mask animated layui-hide"></div>
    <!-- layui.js -->
    <script src="${ctx!}/js/plugins/layui/layui.js"></script>
    <!-- 全局脚本 -->
    <script src="${ctx!}/js/global.js"></script>
    <#--<script src="${ctx!}/js/canvas-particle.js"></script>-->
    <!-- 本页脚本 -->
    <script src="${ctx!}/js/index.js"></script>
    <script src="${ctx!}/js/video.min.js"></script>
    <script type="text/javascript">
        var myPlayer = videojs('my-video');
        videojs("my-video").ready(function(){
            var myPlayer = this;
            myPlayer.play();
        });
    </script>
</body>
</html>