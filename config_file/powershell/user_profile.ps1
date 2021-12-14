# Env
$env:GIT_SSH = "C:\Windows\system32\OpenSSH\ssh.exe"
$env:LESSCHARSET="utf-8"  # for git less
# $env:JAVA_TOOL_OPTIONS=' -Dfile.encoding=UTF-8'
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8  # fix Unrecognizable Code for `findstr` and `grep`

# Alias
Set-Alias vi nvimWithoutConfig
Set-Alias vim vimFunction
Set-Alias ll ls
Set-Alias tig 'D:\learn\Git\usr\bin\tig.exe'
# Set-Alias grep findstr

# function
function nvimWithoutConfig() {
  nvim -u NONE $args[0]
}
function vimFunction() {
  nvim --cmd "let g:skip_plugs=1" -u d:\Note\config_file\vim\nvimConfig\init.vim $args[0]
}
function which ($command) {
  Get-Command -Name $command -ErrorAction SilentlyContinue |
    Select-Object -ExpandProperty Path -ErrorAction SilentlyContinue
}

# oh-my-posh
D:\ProgramFiles\scoop\apps\oh-my-posh\current\bin\oh-my-posh.exe --init --shell pwsh --config D:\Note\config_file\powershell\white_star_rain.omp.json | Invoke-Expression

# powershell module import for pwsh
# other installed module: z
if ($PSVersionTable.PSVersion.Major -eq 7){
  Import-Module posh-git
  Import-Module -Name Terminal-Icons
  
  # PSReadLine
  Set-PSReadLineOption -EditMode Emacs
  Set-PSReadLineOption -BellStyle None
  Set-PSReadLineKeyHandler -Chord 'Ctrl+d' -Function DeleteChar
  Set-PSReadLineOption -PredictionSource History
  Set-PSReadLineOption -PredictionViewStyle ListView
  Set-PSReadlineKeyHandler -Key Tab -Function MenuComplete
  Set-PSReadLineOption -Colors @{
    Command            = 'Magenta'
    Variable           = 'DarkGreen'
    Parameter          = '#48644d'
    Operator           = '#ddfff2'
    Default            = '#ebdbb2'
    # Number             = 'DarkGray'
    # Member             = 'DarkGray'
    # Type               = 'DarkGray'
    # ContinuationPrompt = 'DarkGray'
  }
  # clean command history for PSReadLine
  # Remove-Item (Get-PSReadlineOption).HistorySavePath

  # Fzf
  Import-Module PSFzf
  $env:FZF_DEFAULT_OPTS='--height 40% --reverse --border'
  Set-PsFzfOption -PSReadlineChordProvider 'Ctrl+f' -PSReadlineChordReverseHistory 'Ctrl+r'

}
