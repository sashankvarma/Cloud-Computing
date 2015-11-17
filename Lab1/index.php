<?php
// Start the session
session_start();
?>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $('.first').click(function(){
        var Value = $(fname).val();
        var ajaxurl = 'insert_session.php',
        data =  {'fname': Value};
        $.post(ajaxurl, data, function (response) {
            // Response div goes here.
            $("#message").html("</br>First Name is added as a session variable </br>Please click on the below link to go to next page</br><a href='value.php'>Click here</a>");
        });
    });

});
</script>

	<body>
        <div>
		Please enter your First name and click on submit to save it as a Session Variable
		</div>
		</br>
		<div>
		    <b>First name: </b>
			<input type="text" id="fname">
			<input type="submit" class="first" value="submit"/>
		</div>	
		<div id="message">
		   
		</div>

	</body>
</html>
