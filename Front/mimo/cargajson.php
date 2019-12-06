<?php
  $grupo1 = array("foo1", "bar1", "hello1", "world1");
  $grupo2 = array("foo2", "bar2", "hello2", "world2");

  @$myObj->groups =   array($grupo1,$grupo2);


  $myJSON = json_encode($myObj);

  echo $myJSON;

?>
