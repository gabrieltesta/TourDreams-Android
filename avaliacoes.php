<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$array = array();
	
	$sql = "SELECT m.idMensagem, m.mensagem, m.dataMensagem, c.idCidade, c.cidade, u.idCliente, u.nomeCliente, i.caminhoImagem FROM tbl_mensagemdestino AS m INNER JOIN tbl_cidade AS c ON c.idCidade=m.idCidade INNER JOIN tbl_cliente AS u ON u.idCliente=m.idCliente INNER JOIN tbl_imagem AS i ON i.idImagem=u.idImagem;";
	$select = mysql_query($sql);
	
	while($rows=mysql_fetch_array($select))
	{
		$avaliacao = array(
			"idMensagem"=>$rows['idMensagem'],
			"mensagem"=>$rows['mensagem'],
			"dataMensagem"=>$rows['dataMensagem'],
			"idCidade"=>$rows['idCidade'],
			"cidade"=>$rows['cidade'],
			"idCliente"=>$rows['idCliente'],
			"nomeCliente"=>$rows['nomeCliente'],
			"caminhoImagem"=>$rows['caminhoImagem']
		);
		$array[] = $avaliacao;
	}
	
	echo json_encode($array);

?>