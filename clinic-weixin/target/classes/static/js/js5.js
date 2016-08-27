// JavaScript Document
    var  shuzu=['很差','较差','一般',"很好",'非常好'];
    window.onload=function() {
   
      var oDiv=document.getElementById("rank");
	   var oDiv2=document.getElementById("rank2");
      var aLi=oDiv.getElementsByTagName("li");
      var oP=oDiv.getElementsByTagName("p")[0];
	    var aLi2=oDiv2.getElementsByTagName("li");
      var oP2=oDiv2.getElementsByTagName("p")[0];
      var i=0;
	  var number = oDiv.getElementsByTagName("input");
	   var number2 = oDiv.getElementsByTagName("input");
		for(i=0;i<aLi.length;i++)  {
		   aLi[i].index=i;//为当前加个属性index--》索引值
		      aLi2[i].index=i;//为当前加个属性index--》索引值

        aLi[i].onclick=function(){ //点击将评分结果提交到服务器
  for(i=0;i<=4;i++) {
				  
			     var input = aLi[i].getElementsByTagName("img");
				input[0].src = "image/ratingbar_icon.png"; 
		
			}
		 oP.style.display = "block";
			oP.innerHTML=shuzu[this.index];
			for(i=0;i<=this.index;i++) {
				
			     var input = aLi[i].getElementsByTagName("img");
				input[0].src = "image/ratingbar_icon1.png"; 
		
			}

			number[0].value = i;
		
        }
		     aLi2[i].onclick=function(){ //点击将评分结果提交到服务器
		 for(i=0;i<=4;i++) {
				  
			     var input = aLi2[i].getElementsByTagName("img");
				input[0].src = "image/ratingbar_icon.png"; 
		
			}
     
		 oP2.style.display = "block";
			oP2.innerHTML=shuzu[this.index];
			for(i=0;i<=this.index;i++) {
			     var input = aLi2[i].getElementsByTagName("img");
				input[0].src = "image/ratingbar_icon1.png"; 
		
			}
			number2[0].value = i;
        }
      }
    }

 