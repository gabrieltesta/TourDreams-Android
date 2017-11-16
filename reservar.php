<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');

	$dataEntrada = $_GET['dataEntrada'];
	$dataSaida = $_GET['dataSaida'];
	$idQuarto = $_GET['idQuarto'];
	$nomeTitular = $_GET['nometitular'];
	$numeroCartao = $_GET['numerocartao'];
	$codigoSeguranca = $_GET['codigoseguranca'];
	$validadeCartao = $_GET['validade'];
	$valorTotal = $_GET['valorTotal'];
	$qtdDias = $_GET['qtdDias'];
	
	$valorTotal = str_replace(',', '.', $valorTotal);
	
	date_default_timezone_set("America/Sao_Paulo");
	$datetime = date('Y-m-d H:i:s');
	
	$sql = "SELECT c.idCliente FROM tbl_login AS l INNER JOIN tbl_cliente AS c ON l.idLogin = c.idLogin WHERE l.login='".$_GET['login']."';";
	$select = mysql_query($sql);
	if($rows=mysql_fetch_array($select))
	{
		$idCliente = $rows['idCliente'];
		$sql = "insert into tbl_cartao(numeroCartao,numeroSeguranca, validade,nomeTitular) values('".$numeroCartao."','".$codigoSeguranca."','".$validadeCartao."','".$nomeTitular."');";
		mysql_query($sql) or die(mysql_error());
		$sql = "select LAST_INSERT_ID() as idCartao";
		$select = mysql_query($sql);
		if($rs=mysql_fetch_array($select)){

			$desconto = 0;

			$idCartao = $rs['idCartao'];
			$sql = "insert into tbl_transacao(dataInicio,dataFim,desconto,vlrTotal,dtTransacao,status,idCartao,idQuarto,idCliente,idPlataforma)
					values('".$dataEntrada."','".$dataSaida."',".$desconto.",".$valorTotal.",'".$datetime."','Pendente',".$idCartao.",".$idQuarto." ,".$idCliente.",2);";
			echo $sql;
			if(mysql_query($sql))
			{
				echo 'ok';
			}
			else
			{
				echo 'erro';
			}
		}
		else
		{
			echo 'erro';
		}
	}
	else
	{
		echo 'erro';
	}
?>