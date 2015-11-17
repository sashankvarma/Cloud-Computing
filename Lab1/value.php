<?php
session_start();
?>
<!DOCTYPE html>
<html>
<body>

<?php
// Echo session variables that were set on previous page
echo "First name is " . $_SESSION["first"] . ".<br>";

?>

</body>
</br><a href="index.php">Click here to go to home page</a>
</html>
