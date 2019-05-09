<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>
    <title>${info.metaTitle}</title>
<#include "/templates/layout/metaWeChat.ftl">
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">

    <div class="wrapper wrapper-content">
        <div class="row">

            <div class="col-lg-12">
                <#--<div class="ibox float-e-margins">-->
                    <#--<div class="ibox-content ">-->
                       <#---->
                    <#--</div>-->
                <#--</div>-->
                <div class="ibox float-e-margins">
                    <div class="carousel slide" id="carousel2">
                        <ol class="carousel-indicators">
                        <#list carousels as carousel>
                                <#if carousel_index==0>
                                        <li data-slide-to="${carousel_index}" data-target="#carousel2"  class="active"></li>
                                <#else >
                                     <li data-slide-to="${carousel_index}" data-target="#carousel2"></li>
                                </#if>
                            </#list>
                        </ol>
                        <div class="carousel-inner">

                            <#list carousels as carousel>

                                    <#if carousel_index==0>
                                         <div class="item active">
                                             <img alt="image"  class="img-responsive" src="/picture/${carousel}">
                                         </div>
                                    <#else >
                                        <div class="item">
                                            <img alt="image"  class="img-responsive" src="/picture/${carousel}">
                                        </div>
                                    </#if>
                            </#list>
                            <#--<div class="item active">-->
                                <#--<img alt="image"  class="img-responsive" src="/picture/type1/p_big1.jpg">-->
                                <#--<div class="carousel-caption">-->
                                    <#--&lt;#&ndash;<p>This is simple caption 1</p>&ndash;&gt;-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="item">-->
                                <#--<img alt="image"  class="img-responsive" src="/picture/type1/p_big2.jpg">-->
                                <#--<div class="carousel-caption">-->
                                <#--&lt;#&ndash;<p>This is simple caption 3</p>&ndash;&gt;-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="item ">-->
                                <#--<img alt="image"  class="img-responsive" src="/picture/type1/p_big3.jpg">-->
                                <#--<div class="carousel-caption">-->
                                    <#--&lt;#&ndash;<p>This is simple caption 2</p>&ndash;&gt;-->
                                <#--</div>-->
                            <#--</div>-->

                        </div>
                        <a data-slide="prev" href="#carousel2" class="left carousel-control">
                            <span class="icon-prev"></span>
                        </a>
                        <a data-slide="next" href="#carousel2" class="right carousel-control">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                    <div class="ibox-content">

                        <h2>${info.title}</h2>
                        <p>
                            <#--<strong>照片墙</strong>这是一个照片墙，里面有很多信息，你可以看一下-->
                            <#--<a href="https://github.com/blueimp/Gallery/blob/master/README.md" target="_blank">https://github.com/blueimp/Gallery/blob/master/README.md</a>-->
                            ${info.content}
                        </p>

                        <div class="lightBoxGallery">
                            <#list images as image >
                                <a href="/picture/${image.bigImage}" data-gallery=""><img src="/picture/${image.smallImage}"></a>
                            </#list>
                            <!-- The Gallery as lightbox dialog, should be a child element of the document body -->
                            <div id="blueimp-gallery" class="blueimp-gallery">
                                <div class="slides"></div>
                                <h3 class="title"></h3>
                                <a class="prev">‹</a>
                                <a class="next">›</a>
                                <a class="close">×</a>
                                <a class="play-pause"></a>
                                <ol class="indicator"></ol>
                            </div>

                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <#--</div>-->
<#--<#include "/templates/layout/footer.ftl">-->
</div>

<#include "/templates/layout/commonjs.ftl">
<script src="/static/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<script>
    $(document).ready(function(){
        // $('#carousel2').carousel({
        //     interval: 2000
        // })
        $("#carousel2").carousel('cycle');
    })

</script>

</body>
</html>
