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
                <div class="ibox float-e-margins">

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


</body>
</html>
