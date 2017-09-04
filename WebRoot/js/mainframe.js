 $(function() {
 	
 	$('#btn_query').click(function(){
 	   $.ajax({
 	   	  url: "cslt/getMicrobeResultByBarcode.action",
 	   	  type: "post",
 	   	  data: {barcode: $('#input_barcode').val()}	   	
 	   }).done(function(data){
 	   	  $('#mic_rslt').html('');
 	   	  for(var index in data){
 	   	  	 var rslt = data[index];
 	   	  	 var s = rslt['microbecode'] + '---' + rslt['mic_barcode'] + '---' + rslt['inputman'];
 	   	  	  $('#mic_rslt').append('<span>'+s+'</span>');
 	   	  	
 	   	  }
 	   });
 	   
 	});
 	
 	$('#btn_reset').click(function(){
 	   $.ajax({
 	   	  url: "cslt/delMicrobeResult.action",
 	   	  type: "post",
 	   	  data: {barcode: $('#input_barcode').val(),
 	   	         microbecode: $('#input_microbecode').val(),
 	   	         test: $('#test').val()}	   	
 	   }).done(function(data){
 	   	   alert(data);
 	   });
 	}
 	);
 	

     $('a[target]').click(function() {
         displayLoading();
         //document.getElementById('context').style.height = 0;
     });

     document.getElementById('context').onload = function() {
     	 hideLoading();
         var ifm = document.getElementById('context');
         ifm.style.height = document.getElementById('mainMiddle').scrollHeight + 'px';        
     }

     function displayLoading() {
         var loading = $('<div>').attr('id', 'loading');
         var loadingImage = $('<img>').attr('src', 'pic/loading.gif')
             .css({
                 width: '75px',
                 height: '75px',
                 display: 'block'
             });
         var loadingText = $('<div>正在加载...</div>');
         loading.append(loadingImage);
         loading.append(loadingText);
         $('#mainMiddle').prepend(loading);
         loading.css({
             width: '75px',
             height: '90px',
             display: 'block',
             position: 'absolute',
             top: '50%',
             left: '50%',
             '-webkit-transform': 'translateX(-50%) translateY(-50%)',
             '-moz-transform': 'translateX(-50%) translateY(-50%)',
             '-ms-transform': 'translateX(-50%) translateY(-50%)',
             transform: 'translateX(-50%) translateY(-50%)',
         })
     }

     function hideLoading() {
         $('#loading').remove();
     }
 });
