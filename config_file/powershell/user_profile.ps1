# Env
$env:GIT_SSH = "C:\Windows\system32\OpenSSH\ssh.exe"

# Alias
Set-Alias vi nvimWithoutConfig
Set-Alias vim  nvimWithOriginConfig
Set-Alias ll ls
Set-Alias grep findstr
Set-Alias tig 'D:\learn\Git\usr\bin\tig.exe'

# function
function nvimWithoutConfig() {
  nvim -u NONE $args[0]
}
function nvimWithOriginConfig() {
  nvim -u d:\Note\config_file\vim\nvimConfig\init.vim $args[0]
}

# oh-my-posh
[ScriptBlock]$Prompt = {
    $lastCommandSuccess = $?
    $realLASTEXITCODE = $global:LASTEXITCODE
    $errorCode = 0
    if ($lastCommandSuccess -eq $false) {
        #native app exit code
        if ($realLASTEXITCODE -is [int]) {
            $errorCode = $realLASTEXITCODE
        }
        else {
            $errorCode = 1
        }
    }
    $startInfo = New-Object System.Diagnostics.ProcessStartInfo
    $startInfo.FileName = "$env:SCOOP_GLOBAL\apps\oh-my-posh\current\bin\oh-my-posh.exe"
    $cleanPWD = $PWD.ProviderPath.TrimEnd("\")
    $startInfo.Arguments = "-config=""$env:USERPROFILE\.config\powershell\white_star_rain.omp.json"" -error=$errorCode -pwd=""$cleanPWD"""
    $startInfo.Environment["TERM"] = "xterm-256color"
    $startInfo.CreateNoWindow = $true
    $startInfo.StandardOutputEncoding = [System.Text.Encoding]::UTF8
    $startInfo.RedirectStandardOutput = $true
    $startInfo.UseShellExecute = $false
    if ($PWD.Provider.Name -eq 'FileSystem') {
      $startInfo.WorkingDirectory = $PWD.ProviderPath
    }
    $process = New-Object System.Diagnostics.Process
    $process.StartInfo = $startInfo
    $process.Start() | Out-Null
    $standardOut = $process.StandardOutput.ReadToEnd()
    $process.WaitForExit()
    $standardOut
    $global:LASTEXITCODE = $realLASTEXITCODE
    #remove temp variables
    Remove-Variable realLASTEXITCODE -Confirm:$false
    Remove-Variable lastCommandSuccess -Confirm:$false
}
Set-Item -Path Function:prompt -Value $Prompt -Force


# powershell module import for pwsh
# other installed module: z
if ($PSVersionTable.PSVersion.Major -eq 7){
  Import-Module posh-git
  Import-Module Terminal-Icons
  Import-Module -Name Terminal-Icons
  
  # PSReadLine
  Set-PSReadLineOption -EditMode Emacs
  Set-PSReadLineOption -BellStyle None
  Set-PSReadLineKeyHandler -Chord 'Ctrl+d' -Function DeleteChar
  Set-PSReadLineOption -PredictionSource History
  # Set-PSReadLineOption -PredictionViewStyle ListView

  # Import-Module PSFzf
  # Set-PsFzfOption -PSReadlineChordProvider 'Ctrl+f' -PSReadlineChordReverseHistory 'Ctrl+r'
}
