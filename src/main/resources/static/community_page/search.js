jQuery(function($) { 
    $('#filterText').keyup(function(event) { 
      var val = $(this).val(); if (val == "") { 
        $('.board-item').show(); 
      } else { 
        $('.board-item').hide(); 
        $(".board-item:contains('"+val+"')").show(); 
      } 
    });
  });