// JavaScript Document
if(screen.width<640)
{
         document.getElementById('WebViewport').setAttribute('content', 'width=640, initial-scale=' + screen.width / 640 + ', minimum-scale=0.5, maximum-scale=2.0, user-scalable=no');
}