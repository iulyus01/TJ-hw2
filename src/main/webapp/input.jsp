<!DOCTYPE html>
<html>
<head>
    <title>Graph Upload</title>
</head>
<body>
    <h2>Upload a Graph (DIMACS Format)</h2>

    <form action="result" method="post" enctype="multipart/form-data">
        <label for="file">Select a DIMACS file:</label>
        <input type="file" name="file" id="file" required>
        <br>
        <br>

        <input type="submit" value="Analyze">
    </form>

    <p><b>Application context attribute: </b><span>${applicationScope.data}</span></p>
</body>
</html>
