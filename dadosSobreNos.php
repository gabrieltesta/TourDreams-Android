<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_sobrenos_mobile";
	if($select = mysql_query($sql))
	{
		if($rs = mysql_fetch_array($select))
		{

			echo($rs['descricaoSuperior'].';'.$rs['imgVisao'].';'.$rs['visao'].';'.$rs['imgValores'].';'.$rs['valores'].';'.$rs['imgMissao'].';'.$rs['missao'].';'.$rs['anoUm'].';'.$rs['descricaoUm'].';'.$rs['anoDois'].';'.$rs['descricaoDois'].';'.$rs['anoTres'].';'.$rs['descricaoTres'].';');
		}
		else
		{
			echo ('erro');
		}
	}
	else
	{
		echo ('erro');
	}
?>