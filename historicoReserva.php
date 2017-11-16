<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_reservas_mobile WHERE login='".$_GET['login']."';";
	

	$select = mysql_query($sql);
	
	$array = array();
	
	if($rs=mysql_fetch_array($select))
	{
		$historico = array(
			"idTransacao"=>$rs['idTransacao'],
			"dataInicio"=>$rs['dataInicio'],
			"dataFim"=>$rs['dataFim'],
			"desconto"=>$rs['desconto'],
			"vlrTotal"=>$rs['vlrTotal'],
			"dtTransacao"=>$rs['dtTransacao'],
			"status"=>$rs['status'],
			"hotel"=>$rs['hotel'],
			"caminhoImagem"=>$rs['caminhoImagem']
		);
		$array[] = $historico;
	}
	
	echo json_encode($array);
?>