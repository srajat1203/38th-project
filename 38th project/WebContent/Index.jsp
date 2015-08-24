<!DOCTYPE html>
<html lang="en">
<head>
  <title>Search</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Enter Last Name</h2>
  <form role="form" action="PersonFinder" method = "post" >
    <div class="form-group">
      <label for="search">Person last name:</label>
      <input type="text" class="form-control" id="search" name="search" placeholder="Enter person name ">
       <label for="search2">Company name:</label>
      <input type="text" class="form-control" id="search2" name="search2" placeholder="Enter company name ">
    </div>
  <input type = "submit" value = "Submit"/>
  </form>
</div>

</body>
</html>