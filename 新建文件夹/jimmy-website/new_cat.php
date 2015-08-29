<?php
include_once 'php/config.php';
include_once 'php/db_connect.php';
include_once 'php/loginfunctions.php';
sec_session_start();
// Accessed through URL not submitting
if( !login_check($mysqli) || !isset($_POST['name']) || !isset($_POST['tag']) || !isset($_POST['desc']) || ($_POST['tag'] == 0) || empty($_POST['desc'])) {
		header('Location: ./discussion.php'); 
}

$sql = "SELECT access_level FROM user where id=" . $_SESSION['user_id'];
$result = $mysql->query($sql);
$access_level = 0;

if ($result->num_rows != 1) {
		echo "DATABASE ERROR!";	
		header('Location: ./discussion.php'); 
}
else{
	$row = $result->fetch_assoc();
	$access_level = $row['access_level'];
		if($access_level != 9){
			echo "PERMISSION DENIED!";
			header('Location: ./discussion.php'); 
		}
}
	

// TIMEZONE
// prepare and bind
$stmt = $mysqli->prepare("INSERT INTO category (section, name, desc) VALUES (?, ?, ?)");
$stmt->bind_param("iss", $section, $name, $desc);

// set parameters and execute
$section = $_POST['tag'];
$name = $_POST['name'];
$desc = $_POST['desc'];
$stmt->execute();
$stmt->close();

echo "New category added.";
header('Location: ./discussion.php');

?>