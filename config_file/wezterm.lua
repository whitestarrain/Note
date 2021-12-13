local wezterm = require 'wezterm';

return {
  -- set env for nvim

  font = wezterm.font("MesloLGS NF"),
  -- You can specify some parameters to influence the font selection;
  -- for example, this selects a Bold, Italic font variant.
  -- font = wezterm.font("JetBrains Mono", {weight="Bold", italic=true})

  -- Spawn a fish shell in login mode
  default_prog = {"pwsh"},

  window_background_opacity = 0.85,
  text_background_opacity = 1.0,

  -- set to false to disable the tab bar completely
  enable_tab_bar = true,

  -- set to true to hide the tab bar when there is only
  -- a single tab in the window
  hide_tab_bar_if_only_one_tab = false,

  color_scheme = "Popping and Locking",

  inactive_pane_hsb = {
    saturation = 0.9,
    brightness = 0.8,
  },

  set_environment_variables = { 
    WEZTERM = "wezterm"
  },

  initial_cols = 160,
  initial_rows = 40,
}
