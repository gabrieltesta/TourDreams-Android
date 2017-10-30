<?php


		
	mysql_connect('10.107.144.24', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	
	$idHotel = $_GET['idHotel'];
	$hotel = $_GET['hotel'];
	$checkin = $_GET['checkin'];
	$checkout=$_GET['checkout'];
	$descricao=$_GET['descricao'];
	
	
	$sql = "select * from tbl_hotel where idHotel ='".$idHotel."';";
	$select = mysql_query($sql);
	
	
	if($rs=mysql_fetch_array($select))
	{
		$Hotel = array(
			"idHotel"=>$rs['idHotel'],
			"hotel"=>$rs['hotel'],
			"checkin"=>$rs['checkin'],
			"checkout"=>$rs['checkout'],
			"descricao"=>$rs['descricao']
		
		);
		
		
		$usuarioJSON = json_encode($Hotel);
		
		echo($usuarioJSON);
	}
	

?>