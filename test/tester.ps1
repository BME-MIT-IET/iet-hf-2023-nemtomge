function test([string]$testFile, [string]$expectedFile) {
    # make sure temp.txt exists
    echo "asd" > temp.txt
    # clear content of temporary output file
    Clear-Content temp.txt
    # provide the software the input and write output in temporary output file
    echo $(cat $testFile | java -cp "..\bin ..;../jars/json-simple-1.1.1.jar .;./map.json" main/Main) >> temp.txt

    # get full path of the expected output file
    $pathExpectedFile = Get-Item $expectedFile | Resolve-Path
    # load content of expected output
    $expectedContent = Get-Content -Path $pathExpectedFile

    # get full path of the output file
    $pathOutputFile = Get-Item temp.txt | Resolve-Path
    # load content of the output
    $outputContent = Get-Content -Path $pathOutputFile

    # line counter for other file
    $i = 0
    # number of errors for easier debug
    $errs = 0
    # iterate line by line
    foreach($line in $expectedContent) {
        # remove whitespace
        $exp = $line -replace '\s+',''
        $out = $outputContent[$i] -replace '\s+',''
        # compare lines
        $diff = compare $exp $out
        # if there is any difference
        if (! $diff.Length -eq 0) {
            $errs = $errs + 1
            echo $("Error on line " + $i)
            echo $("Exp: " + $line)
            echo $("Got: " + $outputContent[$i])
            echo "----------"
        }
        $i = $i + 1
    }
    if ($errs -eq 0) {
        echo "Test with test file $testfile passed."
    } else {
        echo "Errs:"
        echo $errs
    }
}

function lazy([string]$arg) {

    # generate filenames
    $testFile = "test" + $(echo $arg) + ".in"
    $expectedFile = "test" + $(echo $arg) + ".out"

    # make sure temp.txt exists
    echo "asd" > temp.txt
    # clear content of temporary output file
    Clear-Content temp.txt
    # provide the software the input and write output in temporary output file
    echo $(cat $testFile | java -cp "..\bin ..;../jars/json-simple-1.1.1.jar .;./map.json" main/Main) >> temp.txt

    # get full path of the expected output file
    $pathExpectedFile = Get-Item $expectedFile | Resolve-Path
    # load content of expected output
    $expectedContent = Get-Content -Path $pathExpectedFile

    # get full path of the output file
    $pathOutputFile = Get-Item temp.txt | Resolve-Path
    # load content of the output
    $outputContent = Get-Content -Path $pathOutputFile

    # line counter for other file
    $i = 0
    # number of errors for easier debug
    $errs = 0
    # iterate line by line
    foreach($line in $expectedContent) {
        # remove whitespace
        $exp = $line -replace '\s+',''
        $out = $outputContent[$i] -replace '\s+',''
        # compare lines
        $diff = compare $exp $out
        # if there is any difference
        if (! $diff.Length -eq 0) {
            $errs = $errs + 1
            echo $("Error on line " + $i)
            echo $("Exp: " + $line)
            echo $("Got: " + $outputContent[$i])
            echo "----------"
        }
        $i = $i + 1
    }
    if ($errs -eq 0) {
        echo "Test with test file $testfile passed."
    } else {
        echo "Errs:"
        echo $errs
    }
}

#test $args[0] $args[1]

lazy $args[0]