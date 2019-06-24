        
    function sendImage(file, el) {
        var form_data = new FormData();
        form_data.append('file', file);
        $.ajax({
            data: form_data,
            type: "POST",
            url: '/file/boardImage',
            cache: false,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function(img_name) {
            	var image = $('<img>').attr({
                    src : "/file/displayFile?fileName="+img_name+"&pathType=boardImage",
                    data_src : img_name                    
                });
                $('#summernote').summernote("insertNode", image[0]);
            }
        });
    }
    
    function deleteImage(src){        
        $.ajax({
            url : "/file/deleteFile",
            type : "post",
            data : {fileName : src,
            	    pathType : "boardImage"}
            ,
            dataType : "text",
            success:function(result){
            	console.log(result);
            }
        });
    }
    
    $(document).ready(function() {
        $('#summernote').summernote({
              placeholder: '',              
              height: 400,
              callbacks: {
            	  onImageUpload: function(files, editor, welEditable) {
                      for (var i = files.length - 1; i >= 0; i--) {
                          sendImage(files[i], this);
                      }
                  },
                  onMediaDelete : function(target) {         
                      deleteImage($(target).attr("data_src"));
                  }
              }     
        });
    });