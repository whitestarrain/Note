local wezterm = require 'wezterm';

return {
  -- set env for nvim

  font = wezterm.font("Hack NF"),
  -- You can specify some parameters to influence the font selection;
  -- for example, this selects a Bold, Italic font variant.
  -- font = wezterm.font("JetBrains Mono", {weight="Bold", italic=true})

  -- Spawn a fish shell in login mode
  default_prog = {"pwsh"},

  window_background_opacity = 0.85,
  text_background_opacity = 0.90,

  -- set to false to disable the tab bar completely
  enable_tab_bar = true,

  -- set to true to hide the tab bar when there is only
  -- a single tab in the window
  hide_tab_bar_if_only_one_tab = false,


  inactive_pane_hsb = {
    saturation = 0.9,
    brightness = 0.8,
  },

  set_environment_variables = { 
    WEZTERM = "wezterm"
  },

  initial_cols = 160,
  initial_rows = 45,

  -- tab bar appearance config
  -- enable_tab_bar=false,
  -- tab_bar_at_bottom = true,
  -- tab_bar_color
  colors = {
    tab_bar = {

      -- The color of the strip that goes along the top of the window
      background = "#1a1a21",

      -- The active tab is the one that has focus in the window
      active_tab = {
        -- The color of the background area for the tab
        bg_color = "#2b2042",
        -- The color of the text for the tab
        fg_color = "#c0c0c0",

        -- Specify whether you want "Half", "Normal" or "Bold" intensity for the
        -- label shown for this tab.
        -- The default is "Normal"
        intensity = "Normal",

        -- Specify whether you want "None", "Single" or "Double" underline for
        -- label shown for this tab.
        -- The default is "None"
        underline = "None",

        -- Specify whether you want the text to be italic (true) or not (false)
        -- for this tab.  The default is false.
        italic = false,

        -- Specify whether you want the text to be rendered with strikethrough (true)
        -- or not for this tab.  The default is false.
        strikethrough = false,
      },

      -- Inactive tabs are the tabs that do not have focus
      inactive_tab = {
        bg_color = "#1b1032",
        fg_color = "#808080",

        -- The same options that were listed under the `active_tab` section above
        -- can also be used for `inactive_tab`.
      },

      -- You can configure some alternate styling when the mouse pointer
      -- moves over inactive tabs
      inactive_tab_hover = {
        bg_color = "#3b3052",
        fg_color = "#909090",
        italic = true,

        -- The same options that were listed under the `active_tab` section above
        -- can also be used for `inactive_tab_hover`.
      },

      -- The new tab button that let you create new tabs
      new_tab = {
        bg_color = "#1b1032",
        fg_color = "#808080",

        -- The same options that were listed under the `active_tab` section above
        -- can also be used for `new_tab`.
      },

      -- You can configure some alternate styling when the mouse pointer
      -- moves over the new tab button
      new_tab_hover = {
        bg_color = "#3b3052",
        fg_color = "#909090",
        italic = true,

        -- The same options that were listed under the `active_tab` section above
        -- can also be used for `new_tab_hover`.
      }
    },
    -- the foreground color of selected text
    selection_fg = "black",
    -- the background color of selected text
    selection_bg = "#fffacd",

    -- The color of the scrollbar "thumb"; the portion that represents the current viewport
    scrollbar_thumb = "#222222",

    -- The color of the split lines between panes
    split = "#444444",

    ansi = {"black", "maroon", "green", "olive", "navy", "purple", "teal", "silver"},
    brights = {"grey", "red", "lime", "yellow", "blue", "fuchsia", "aqua", "white"},

    -- Arbitrary colors of the palette in the range from 16 to 255
    indexed = {[136] = "#af8700"},
  },

  -- windows padding
  window_padding = {
    left = "1cell",
    right = "1cell",
    top = "0cell",
    bottom = "0cell",
  },

  -- color_schemes overwrite colors!
  color_schemes = {
  -- ref: https://github.com/wez/wezterm/blob/main/assets/colors/OneHalfDark.toml
      ["My one dark"] = {
        foreground = "#ebdbb2",
        background = "#181921",
        cursor_bg = "#74e0c8",
        cursor_fg = "black",
        cursor_border = "#52ad70",
        selection_bg = "#ebdbb2",
        selection_fg = "black",
        ansi = {"#282c34","#e06c75","#98c379","#e5c07b","#61afef","#c678dd","#56b6c2","#dcdfe4"},
        brights = {"#282c34","#e06c75","#98c379","#e5c07b","#61afef","#c678dd","#56b6c2","#dcdfe4"},
    },
  },
  color_scheme = "My one dark",
}
