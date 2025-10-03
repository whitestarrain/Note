> <https://www.gnu.org/software/emacs/manual/html_node/emacs/index.html#SEC_Contents>

## 1 The Organization of the Screen

On a graphical display, such as on GNU/Linux using the X Window System, Emacs occupies a graphical window. On a text terminal, Emacs occupies the entire terminal screen. We will use the term _frame_ to mean a graphical window or terminal screen occupied by Emacs. Emacs behaves very similarly on both kinds of frames. It normally starts out with just one frame, but you can create additional frames if you wish (see [Frames and Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frames.html)).

Each frame consists of several distinct regions. At the top of the frame is a _menu bar_, which allows you to access commands via a series of menus. On a graphical display, directly below the menu bar is a _tool bar_, a row of icons that perform editing commands when you click on them. At the very bottom of the frame is an _echo area_, where informative messages are displayed and where you enter information when Emacs asks for it.

The main area of the frame, below the tool bar (if one exists) and above the echo area, is called _the window_. Henceforth in this manual, we will use the word “window” in this sense. Graphical display systems commonly use the word “window” with a different meaning; but, as stated above, we refer to those graphical windows as “frames”.

An Emacs window is where the _buffer_—the text or other graphics you are editing or viewing—is displayed. On a graphical display, the window possesses a _scroll bar_ on one side, which can be used to scroll through the buffer. The last line of the window is a _mode line_. This displays various information about what is going on in the buffer, such as whether there are unsaved changes, the editing modes that are in use, the current line number, and so forth.

When you start Emacs, there is normally only one window in the frame. However, you can subdivide this window horizontally or vertically to create multiple windows, each of which can independently display a buffer (see [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html)).

At any time, one window is the _selected window_. On a graphical display, the selected window shows a more prominent cursor (usually solid and blinking); other windows show a less prominent cursor (usually a hollow box). On a text terminal, there is only one cursor, which is shown in the selected window. The buffer displayed in the selected window is called the _current buffer_, and it is where editing happens. Most Emacs commands implicitly apply to the current buffer; the text displayed in unselected windows is mostly visible for reference. If you use multiple frames on a graphical display, selecting a particular frame selects a window in that frame.

### 1.1 Point

The cursor in the selected window shows the location where most editing commands take effect, which is called _point_. Many Emacs commands move point to different places in the buffer; for example, you can place point by clicking mouse button 1 (normally the left button) at the desired location.

By default, the cursor in the selected window is drawn as a solid block and appears to be _on_ a character, but you should think of point as _between_ two characters; it is situated _before_ the character under the cursor. For example, if your text looks like ‘frob’ with the cursor over the ‘b’, then point is between the ‘o’ and the ‘b’. If you insert the character ‘!’ at that position, the result is ‘fro!b’, with point between the ‘!’ and the ‘b’. Thus, the cursor remains over the ‘b’, as before.

If you are editing several files in Emacs, each in its own buffer, each buffer has its own value of point. A buffer that is not currently displayed remembers its value of point if you later display it again. Furthermore, if a buffer is displayed in multiple windows, each of those windows has its own value of point.

See [Displaying the Cursor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cursor-Display.html), for options that control how Emacs displays the cursor.

> The term “point” comes from the character ‘.’, which was the command in TECO (the language in which the original Emacs was written) for accessing the editing position.

### 1.2 The Echo Area

The line at the very bottom of the frame is the _echo area_. It is used to display small amounts of text for various purposes.

The echo area is so-named because one of the things it is used for is _echoing_, which means displaying the characters of a multi-character command as you type. Single-character commands are not echoed. Multi-character commands (see [Keys](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keys.html)) are echoed if you pause for more than a second in the middle of a command. Emacs then echoes all the characters of the command so far, to prompt you for the rest. Once echoing has started, the rest of the command echoes immediately as you type it. This behavior is designed to give confident users fast response, while giving hesitant users maximum feedback.

The echo area is also used to display an _error message_ when a command cannot do its job. Error messages may be accompanied by beeping or by flashing the screen.

Some commands display informative messages in the echo area to tell you what the command has done, or to provide you with some specific information. These _informative_ messages, unlike error messages, are not accompanied with a beep or flash. For example, C-x = (hold down Ctrl and type x, then let go of Ctrl and type =) displays a message describing the character at point, its position in the buffer, and its current column in the window. Commands that take a long time often display messages ending in ‘...’ while they are working (sometimes also indicating how much progress has been made, as a percentage), and add ‘done’ when they are finished.

Informative echo area messages are saved in a special buffer named _Messages_. (We have not explained buffers yet; see [Using Multiple Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Buffers.html), for more information about them.) If you miss a message that appeared briefly on the screen, you can switch to the _Messages_ buffer to see it again. The _Messages_ buffer is limited to a certain number of lines, specified by the variable `message-log-max`. (We have not explained variables either; see [Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Variables.html), for more information about them.) Beyond this limit, one line is deleted from the beginning whenever a new message line is added at the end.

See [Customization of Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display-Custom.html), for options that control how Emacs uses the echo area.

The echo area is also used to display the _minibuffer_, a special window where you can input arguments to commands, such as the name of a file to be edited. When the minibuffer is in use, the text displayed in the echo area begins with a _prompt string_, and the active cursor appears within the minibuffer, which is temporarily considered the selected window. You can always get out of the minibuffer by typing C-g. See [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html).

### 1.3 The Mode Line

At the bottom of each window is a _mode line_, which describes what is going on in the current buffer. When there is only one window, the mode line appears right above the echo area; it is the next-to-last line in the frame. On a graphical display, the mode line is drawn with a 3D box appearance. Emacs also usually draws the mode line of the selected window with a different color from that of unselected windows, in order to make it stand out.

The text displayed in the mode line has the following format:

```
 cs:ch-dfr  buf      pos line   (major minor)
```

On a text terminal, this text is followed by a series of dashes extending to the right edge of the window. These dashes are omitted on a graphical display.

The cs string and the colon character after it describe the character set and newline convention used for the current buffer. Normally, Emacs automatically handles these settings for you, but it is sometimes useful to have this information.

cs describes the character set of the text in the buffer (see [Coding Systems](https://www.gnu.org/software/emacs/manual/html_node/emacs/Coding-Systems.html)). If it is a dash (‘-’), that indicates no special character set handling (with the possible exception of end-of-line conventions, described in the next paragraph). ‘=’ means no conversion whatsoever, and is usually used for files containing non-textual data. Other characters represent various _coding systems_—for example, ‘1’ represents ISO Latin-1.

On a text terminal, cs is preceded by two additional characters that describe the coding systems for keyboard input and terminal output. Furthermore, if you are using an input method, cs is preceded by a string that identifies the input method (see [Input Methods](https://www.gnu.org/software/emacs/manual/html_node/emacs/Input-Methods.html)).

The character after cs is usually a colon. If a different string is displayed, that indicates a nontrivial end-of-line convention for encoding a file. Usually, lines of text are separated by _newline characters_ in a file, but two other conventions are sometimes used. The MS-DOS convention uses a carriage return character followed by a linefeed character; when editing such files, the colon changes to either a backslash (‘\’) or ‘(DOS)’, depending on the operating system. Another convention, employed by older Macintosh systems, uses a carriage return character instead of a newline; when editing such files, the colon changes to either a forward slash (‘/’) or ‘(Mac)’. On some systems, Emacs displays ‘(Unix)’ instead of the colon for files that use newline as the line separator.

On frames created for `emacsclient` (see [Invoking `emacsclient`](https://www.gnu.org/software/emacs/manual/html_node/emacs/Invoking-emacsclient.html)), the next character is ‘@’. This indication is typical for frames of an Emacs process running as a daemon (see [Using Emacs as a Server](https://www.gnu.org/software/emacs/manual/html_node/emacs/Emacs-Server.html)).

The next element on the mode line is the string indicated by ch. This shows two dashes (‘--’) if the buffer displayed in the window has the same contents as the corresponding file on the disk; i.e., if the buffer is unmodified. If the buffer is modified, it shows two stars (‘\*_’). For a read-only buffer, it shows ‘%_’ if the buffer is modified, and ‘%%’ otherwise.

The character after ch is normally a dash (‘-’). However, if `default-directory` (see [File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Names.html)) for the current buffer is on a remote machine, ‘@’ is displayed instead.

d appears if the window is dedicated to its current buffer. It appears as ‘D’ for strong dedication and ‘d’ for other forms of dedication. If the window is not dedicated, d does not appear. See [(The Emacs Lisp Reference Manual)elisp](https://www.gnu.org/software/emacs/manual/html_node/The Emacs Lisp Reference Manual_html/Dedicated-Windows.html#Dedicated-Windows).

fr gives the selected frame name (see [Frames and Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frames.html)). It appears only on text terminals. The initial frame’s name is ‘F1’.

buf is the name of the buffer displayed in the window. Usually, this is the same as the name of a file you are editing. See [Using Multiple Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Buffers.html).

pos tells you whether there is additional text above the top of the window, or below the bottom. If your buffer is small and all of it is visible in the window, pos is ‘All’. Otherwise, it is ‘Top’ if you are looking at the beginning of the buffer, ‘Bot’ if you are looking at the end of the buffer, or ‘nn%’, where nn is the percentage of the buffer above the top of the window. With Size Indication mode, you can display the size of the buffer as well. See [Optional Mode Line Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Optional-Mode-Line.html).

line is the character ‘L’ followed by the line number at point. (You can display the current column number too, by turning on Column Number mode. See [Optional Mode Line Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Optional-Mode-Line.html).)

major is the name of the _major mode_ used in the buffer. A major mode is a principal editing mode for the buffer, such as Text mode, Lisp mode, C mode, and so forth. See [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html). Some major modes display additional information after the major mode name. For example, Compilation buffers and Shell buffers display the status of the subprocess.

minor is a list of some of the enabled _minor modes_, which are optional editing modes that provide additional features on top of the major mode. See [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html).

Some features are listed together with the minor modes whenever they are turned on, even though they are not really minor modes. ‘Narrow’ means that the buffer being displayed has editing restricted to only a portion of its text (see [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html)). ‘Def’ means that a keyboard macro is currently being defined (see [Keyboard Macros](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macros.html)).

In addition, if Emacs is inside a recursive editing level, square brackets (‘[…]’) appear around the parentheses that surround the modes. If Emacs is in one recursive editing level within another, double square brackets appear, and so on. Since recursive editing levels affect Emacs globally, such square brackets appear in the mode line of every window. See [Recursive Editing Levels](https://www.gnu.org/software/emacs/manual/html_node/emacs/Recursive-Edit.html).

You can change the appearance of the mode line as well as the format of its contents. See [Optional Mode Line Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Optional-Mode-Line.html). In addition, the mode line is mouse-sensitive; clicking on different parts of the mode line performs various commands. See [Mode Line Mouse Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line-Mouse.html). Also, hovering the mouse pointer above mouse-sensitive portions of the mode line shows tooltips (see [Tooltips](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tooltips.html)) with information about commands you can invoke by clicking on the mode line.

### 1.4 The Menu Bar

Each Emacs frame normally has a _menu bar_ at the top which you can use to perform common operations. There’s no need to list them here, as you can more easily see them yourself.

On a display that supports a mouse, you can use the mouse to choose a command from the menu bar. An arrow on the right edge of a menu item means it leads to a subsidiary menu, or _submenu_. A ‘...’ at the end of a menu item means that the command will prompt you for further input before it actually does anything.

Some of the commands in the menu bar have ordinary key bindings as well; if so, a key binding is shown after the item itself. To view the full command name and documentation for a menu item, type C-h k, and then select the menu bar with the mouse in the usual way (see [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html)).

Instead of using the mouse, you can also invoke the first menu bar item by pressing F10 (to run the command `menu-bar-open`). You can then navigate the menus with the arrow keys or with C-b, C-f (left/right), C-p, and C-n (up/down). To activate a selected menu item, press RET; to cancel menu navigation, press C-g or ESC ESC ESC. (However, note that when Emacs was built with a GUI toolkit, the menus are drawn and controlled by the toolkit, and the key sequences to cancel menu navigation might be different from the above description.)

On a text terminal, you can optionally access the menu-bar menus in the echo area. To this end, customize the variable `tty-menu-open-use-tmm` to a non-`nil` value. Then typing F10 will run the command `tmm-menubar` instead of dropping down the menu. (You can also type M-`, which always invokes `tmm-menubar`.)  `tmm-menubar` lets you select a menu item with the keyboard. A provisional choice appears in the echo area. You can use the up and down arrow keys to move through the menu to different items, and then you can type RET to select the item. Each menu item is also designated by a letter or digit (usually the initial of some word in the item’s name). This letter or digit is separated from the item name by ‘==>’. You can type the item’s letter or digit to select the item.

## 2 Kinds of User Input

GNU Emacs is primarily designed for use with the keyboard. While it is possible to use the mouse to issue editing commands through the menu bar and tool bar, that is usually not as efficient as using the keyboard.

Keyboard input into Emacs is based on a heavily-extended version of ASCII. Simple characters, like ‘a’, ‘B’, ‘3’, ‘=’, and the space character (denoted as SPC), are entered by typing the corresponding key. _Control characters_, such as RET, TAB, DEL, ESC, F1, Home, and LEFT, are also entered this way, as are certain characters found on non-English keyboards (see [International Character Set Support](https://www.gnu.org/software/emacs/manual/html_node/emacs/International.html)).

Emacs also recognizes control characters that are entered using _modifier keys_. Two commonly-used modifier keys are Control (usually labeled Ctrl), and Meta (usually labeled Alt). For example, Control-a is entered by holding down the Ctrl key while pressing a; we will refer to this as C-a for short. Similarly, Meta-a, or M-a for short, is entered by holding down the Alt key and pressing a. Modifier keys can also be applied to non-alphanumerical characters, e.g., C-F1 or M-LEFT.

> We refer to Alt as Meta for historical reasons.

You can also type Meta characters using two-character sequences starting with ESC. Thus, you can enter M-a by typing ESC a. You can enter C-M-a (holding down both Ctrl and Alt, then pressing a) by typing ESC C-a. Unlike Meta, ESC is entered as a separate character. You don’t hold down ESC while typing the next character; instead, press ESC and release it, then enter the next character. This feature is useful on certain text terminals where the Meta key does not function reliably.

Emacs supports 3 additional modifier keys, see [Modifier Keys](https://www.gnu.org/software/emacs/manual/html_node/emacs/Modifier-Keys.html).

Emacs has extensive support for using mouse buttons, mouse wheels and other pointing devices like touchpads and touch screens. See [Mouse Input](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Input.html), for details.

On graphical displays, the window manager might block some keyboard inputs, including M-TAB, M-SPC, C-M-d and C-M-l. If you have this problem, you can either customize your window manager to not block those keys, or rebind the affected Emacs commands (see [Customization](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customization.html)).

Simple characters and control characters, as well as certain non-keyboard inputs such as mouse clicks, are collectively referred to as _input events_. For details about how Emacs internally handles input events, see [Input Events](https://www.gnu.org/software/emacs/manual/html_node/elisp/Input-Events.html#Input-Events) in The Emacs Lisp Reference Manual.

## 3 Keys

Some Emacs commands are invoked by just one input event; for example, C-f moves forward one character in the buffer. Other commands take two or more input events to invoke, such as C-x C-f and C-x 4 C-f.

A _key sequence_, or _key_ for short, is a sequence of one or more input events that is meaningful as a unit. If a key sequence invokes a command, we call it a _complete key_; for example, C-f, C-x C-f and C-x 4 C-f are all complete keys. If a key sequence isn’t long enough to invoke a command, we call it a _prefix key_; from the preceding example, we see that C-x and C-x 4 are prefix keys. Every key sequence is either a complete key or a prefix key.

A prefix key combines with the following input event to make a longer key sequence. For example, C-x is a prefix key, so typing C-x alone does not invoke a command; instead, Emacs waits for further input (if you pause for longer than a second, it echoes the C-x key to prompt for that input; see [The Echo Area](https://www.gnu.org/software/emacs/manual/html_node/emacs/Echo-Area.html)). C-x combines with the next input event to make a two-event key sequence, which could itself be a prefix key (such as C-x 4), or a complete key (such as C-x C-f). There is no limit to the length of key sequences, but in practice they are seldom longer than three or four input events.

You can’t add input events onto a complete key. For example, because C-f is a complete key, the two-event sequence C-f C-k is two key sequences, not one.

By default, the prefix keys in Emacs are C-c, C-h, C-x, C-x RET, C-x @, C-x a, C-x n, C-x r, C-x t, C-x v, C-x 4, C-x 5, C-x 6, ESC, and M-g. (F1 and F2 are aliases for C-h and C-x 6.) This list is not cast in stone; if you customize Emacs, you can make new prefix keys. You could even eliminate some of the standard ones, though this is not recommended for most users; for example, if you remove the prefix definition of C-x 4, then C-x 4 C-f becomes an invalid key sequence. See [Customizing Key Bindings](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Bindings.html).

Typing the help character (C-h or F1) after a prefix key displays a list of the commands starting with that prefix. The sole exception to this rule is ESC: ESC C-h is equivalent to C-M-h, which does something else entirely. You can, however, use F1 to display a list of commands starting with ESC.

## 4 Mouse Input

By default, Emacs supports all the normal mouse actions like setting the cursor by clicking on the left mouse button, and selecting an area by dragging the mouse pointer. All mouse actions can be used to bind commands in the same way you bind them to keyboard events (see [Keys](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keys.html)). This section provides a general overview of using the mouse in Emacs; see [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html), and the sections that follow it, for more details about mouse commands in Emacs.

When you click the left mouse button, Emacs receives a `mouse-1` event. To see what command is bound to that event, you can type C-h c and then press the left mouse button. Similarly, the middle mouse button is `mouse-2` and the right mouse button is `mouse-3`. If you have a mouse with a wheel, the wheel events are commonly bound to either `wheel-down` or `wheel-up`, or `mouse-4` and `mouse-5`, but that depends on the operating system configuration.

In general, legacy X systems and terminals (see [Using a Mouse in Text Terminals](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text_002dOnly-Mouse.html)) will report `mouse-4` and `mouse-5`, while all other systems will report `wheel-down` and `wheel-up`.

Some mice also have a horizontal scroll wheel, and touchpads usually support scrolling horizontally as well. These events are reported as `wheel-left` and `wheel-right` on all systems other than terminals and legacy X systems, where they are `mouse-6` and `mouse-7`.

You can also combine keyboard modifiers with mouse events, so you can bind a special command that triggers when you, for instance, holds down the Meta key and then uses the middle mouse button. In that case, the event name will be `M-mouse-2`.

On some systems, you can also bind commands for handling touch screen events. In that case, the events are called `touchscreen-update` and `touchscreen-end`.

## 5 Keys and Commands

This manual is full of passages that tell you what particular keys do. But Emacs does not assign meanings to keys directly. Instead, Emacs assigns meanings to named _commands_, and then gives keys their meanings by _binding_ them to commands.

Every command has a name chosen by a programmer. The name is usually made of a few English words separated by dashes; for example, `next-line` or `forward-word`. Internally, each command is a special type of Lisp _function_, and the actions associated with the command are performed by running the function. See [What Is a Function](https://www.gnu.org/software/emacs/manual/html_node/elisp/What-Is-a-Function.html#What-Is-a-Function) in The Emacs Lisp Reference Manual.

The bindings between keys and commands are recorded in tables called _keymaps_. See [Keymaps](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keymaps.html).

When we say that “C-n moves down vertically one line” we are glossing over a subtle distinction that is irrelevant in ordinary use, but vital for Emacs customization. The command `next-line` does a vertical move downward. C-n has this effect _because_ it is bound to `next-line`. If you rebind C-n to the command `forward-word`, C-n will move forward one word instead.

In this manual, we will often speak of keys like C-n as commands, even though strictly speaking the key is bound to a command. Usually, we state the name of the command which really does the work in parentheses after mentioning the key that runs it. For example, we will say that “The command C-n (`next-line`) moves point vertically down”, meaning that the command `next-line` moves vertically down, and the key C-n is normally bound to it.

Since we are discussing customization, we should tell you about _variables_. Often the description of a command will say, “To change this, set the variable `mumble-foo`.” A variable is a name used to store a value. Most of the variables documented in this manual are meant for customization: some command or other part of Emacs examines the variable and behaves differently according to the value that you set. You can ignore the information about variables until you are interested in customizing them. Then read the basic information on variables (see [Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Variables.html)) and the information about specific variables will make sense.

## 6 Touchscreen Input and Virtual Keyboards

Emacs was first written assuming that its users were to use it from a desktop computer or computer terminal, equipped with a keyboard and perhaps a suitable pointing device such as a mouse (see [Mouse Input](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Input.html)).

Emacs is also capable of receiving input from alternative sources of input, enabling users to interact with it even if it is installed on a computer that substitutes such input sources for the customary combination of keyboard and mouse.

- [Using Emacs on Touchscreens](https://www.gnu.org/software/emacs/manual/html_node/emacs/Touchscreens.html)
- [Using Emacs with Virtual Keyboards](https://www.gnu.org/software/emacs/manual/html_node/emacs/On_002dScreen-Keyboards.html)

## 7 Entering Emacs

The usual way to invoke Emacs is with the shell command `emacs`. From a terminal window running a Unix shell on a GUI terminal, you can run Emacs in the background with emacs &; this way, Emacs won’t tie up the terminal window, so you can use it to run other shell commands. (For comparable methods of starting Emacs on MS-Windows, see [How to Start Emacs on MS-Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows-Startup.html).)

When Emacs starts up, the initial frame displays a special buffer named ‘_GNU Emacs_’. This _startup screen_ contains information about Emacs and _links_ to common tasks that are useful for beginning users. For instance, activating the ‘Emacs Tutorial’ link opens the Emacs tutorial; this does the same thing as the command C-h t (`help-with-tutorial`). To activate a link, either move point onto it and type RET, or click on it with mouse-1 (the left mouse button).

Using a command line argument, you can tell Emacs to visit one or more files as soon as it starts up. For example, `emacs foo.txt` starts Emacs with a buffer displaying the contents of the file ‘foo.txt’. This feature exists mainly for compatibility with other editors, which are designed to be launched from the shell for short editing sessions. If you call Emacs this way, the initial frame is split into two windows—one showing the specified file, and the other showing the startup screen. See [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html).

Generally, it is unnecessary and wasteful to start Emacs afresh each time you want to edit a file. The recommended way to use Emacs is to start it just once, just after you log in, and do all your editing in the same Emacs session. See [File Handling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Files.html), for information on visiting more than one file. If you use Emacs this way, the Emacs session accumulates valuable context, such as the kill ring, registers, undo history, and mark ring data, which together make editing more convenient. These features are described later in the manual.

To edit a file from another program while Emacs is running, you can use the `emacsclient` helper program to open a file in the existing Emacs session. See [Using Emacs as a Server](https://www.gnu.org/software/emacs/manual/html_node/emacs/Emacs-Server.html).

Emacs accepts other command line arguments that tell it to load certain Lisp files, where to put the initial frame, and so forth. See [Command Line Arguments for Emacs Invocation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Emacs-Invocation.html).

If the variable `inhibit-startup-screen` is non-`nil`, Emacs does not display the startup screen. In that case, if one or more files were specified on the command line, Emacs simply displays those files; otherwise, it displays a buffer named _scratch_, which can be used to evaluate Emacs Lisp expressions interactively. See [Lisp Interaction Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lisp-Interaction.html). You can set the variable `inhibit-startup-screen` using the Customize facility (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)), or by editing your initialization file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)).

> Setting `inhibit-startup-screen` in site-start.el doesn’t work, because the startup screen is set up before reading site-start.el. See [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html), for information about site-start.el.

You can also force Emacs to display a file or directory at startup by setting the variable `initial-buffer-choice` to a string naming that file or directory. The value of `initial-buffer-choice` may also be a function (of no arguments) that should return a buffer which is then displayed. If `initial-buffer-choice` is non-`nil`, then if you specify any files on the command line, Emacs still visits them, but does not display them initially.

## 8 Exiting Emacs

- C-x C-c

  Kill Emacs (`save-buffers-kill-terminal`).

- C-z

  On a text terminal, suspend Emacs; on a graphical display, iconify (or “minimize”) the selected frame (`suspend-frame`).

_Killing_ Emacs means terminating the Emacs program. To do this, type C-x C-c (`save-buffers-kill-terminal`). A two-character key sequence is used to make it harder to type by accident. If there are any modified file-visiting buffers when you type C-x C-c, Emacs first offers to save these buffers. If you do not save them all, it asks for confirmation again, since the unsaved changes will be lost. Emacs also asks for confirmation if any subprocesses are still running, since killing Emacs will also kill the subprocesses (see [Running Shell Commands from Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shell.html)).

C-x C-c behaves specially if you are using Emacs as a server. If you type it from a client frame, it closes the client connection. See [Using Emacs as a Server](https://www.gnu.org/software/emacs/manual/html_node/emacs/Emacs-Server.html).

Emacs can, optionally, record certain session information when you kill it, such as the files you were visiting at the time. This information is then available the next time you start Emacs. See [Saving Emacs Sessions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Saving-Emacs-Sessions.html).

If the value of the variable `confirm-kill-emacs` is non-`nil`, C-x C-c assumes that its value is a predicate function, and calls that function. If the result of the function call is non-`nil`, the session is killed, otherwise Emacs continues to run. One convenient function to use as the value of `confirm-kill-emacs` is the function `yes-or-no-p`. The default value of `confirm-kill-emacs` is `nil`.

If the value of the variable `confirm-kill-processes` is `nil`, C-x C-c does not ask for confirmation before killing subprocesses started by Emacs. The value is `t` by default.

To further customize what happens when Emacs is exiting, see [Killing Emacs](https://www.gnu.org/software/emacs/manual/html_node/elisp/Killing-Emacs.html#Killing-Emacs) in The GNU Emacs Lisp Reference Manual.

To kill Emacs without being prompted about saving, type M-x kill-emacs.

C-z runs the command `suspend-frame`. On a graphical display, this command _minimizes_ (or _iconifies_) the selected Emacs frame, hiding it in a way that lets you bring it back later (exactly how this hiding occurs depends on the window system). On a text terminal, the C-z command _suspends_ Emacs, stopping the program temporarily and returning control to the parent process (usually a shell); in most shells, you can resume Emacs after suspending it with the shell command `%emacs`.

Text terminals usually listen for certain special characters whose meaning is to kill or suspend the program you are running. **This terminal feature is turned off while you are in Emacs.** The meanings of C-z and C-x C-c as keys in Emacs were inspired by the use of C-z and C-c on several operating systems as the characters for stopping or killing a program, but that is their only relationship with the operating system. You can customize these keys to run any commands of your choice (see [Keymaps](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keymaps.html)).

## 9 Basic Editing Commands

Here we explain the basics of how to enter text, make corrections, and save the text in a file. If this material is new to you, we suggest you first run the Emacs learn-by-doing tutorial, by typing C-h t (`help-with-tutorial`).

### 9.1 Inserting Text

You can insert an ordinary _graphic character_ (e.g., ‘a’, ‘B’, ‘3’, and ‘=’) by typing the associated key. This adds the character to the buffer at point. Insertion moves point forward, so that point remains just after the inserted text. See [Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/Point.html).

To end a line and start a new one, type RET (`newline`). (The RET key may be labeled Return, or Enter, or with a funny-looking left-pointing arrow on your keyboard, but we refer to it as RET in this manual.) This command inserts a newline character into the buffer, then indents (see [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html)) according to the major mode. If point is at the end of the line, the effect is to create a new blank line after it and indent the new line; if point is in the middle of a line, the line is split at that position. To turn off the auto-indentation, you can either disable Electric Indent mode (see [Convenience Features for Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indent-Convenience.html)) or type C-j, which inserts just a newline, without any auto-indentation.

As we explain later in this manual, you can change the way Emacs handles text insertion by turning on _minor modes_. For instance, the minor mode called Auto Fill mode splits lines automatically when they get too long (see [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html)). The minor mode called Overwrite mode causes inserted characters to replace (overwrite) existing text, instead of shoving it to the right. See [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html).

Only graphic characters can be inserted by typing the associated key; other keys act as editing commands and do not insert themselves. For instance, DEL runs the command `delete-backward-char` by default (some modes bind it to a different command); it does not insert a literal ‘DEL’ character (ASCII character code 127).

To insert a non-graphic character, or a character that your keyboard does not support, first _quote_ it by typing C-q (`quoted-insert`). There are two ways to use C-q:

- C-q followed by any non-graphic character (even C-g) inserts that character. For instance, C-q DEL inserts a literal ‘DEL’ character.

- C-q

  followed by a sequence of octal digits inserts the character with the specified octal character code. You can use any number of octal digits; any non-digit terminates the sequence. If the terminating character is

  RET

  , that

  RET

  serves only to terminate the sequence. Any other non-digit terminates the sequence and then acts as normal input—thus,

  C-q 1 0 1 B

  inserts ‘

  AB

  ’.

  The use of octal sequences is disabled in ordinary non-binary Overwrite mode, to give you a convenient way to insert a digit instead of overwriting with it.

To use decimal or hexadecimal instead of octal, set the variable `read-quoted-char-radix` to 10 or 16. If the radix is 16, the letters a to f serve as part of a character code, just like digits. Case is ignored.

A few common Unicode characters can be inserted via a command starting with C-x 8. For example, C-x 8 [ inserts `‘` which is Unicode code-point U+2018 LEFT SINGLE QUOTATION MARK, sometimes called a left single “curved quote” or “curly quote”. Similarly, C-x 8 ], C-x 8 { and C-x 8 } insert the curved quotes `’`, `“` and `”`, respectively. Also, a working Alt key acts like C-x 8 (unless followed by RET); e.g., A-[ acts like C-x 8 [ and inserts `‘`. To see which characters have C-x 8 shorthands, type C-x 8 C-h.

Alternatively, you can use the command C-x 8 RET (`insert-char`). This prompts for the Unicode name or code-point of a character, using the minibuffer. If you enter a name, the command provides completion (see [Completion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion.html)). If you enter a code-point, it should be as a hexadecimal number (the convention for Unicode), or a number with a specified radix, e.g., `#o23072` (octal); See [Integer Basics](https://www.gnu.org/software/emacs/manual/html_node/elisp/Integer-Basics.html#Integer-Basics) in The Emacs Lisp Reference Manual. The command then inserts the corresponding character into the buffer.

For example, the following all insert the same character:

```
C-x 8 RET left single quotation mark RET
C-x 8 RET left sin TAB RET
C-x 8 RET 2018 RET
C-x 8 [
A-[  (if the Alt key works)
`    (in Electric Quote mode)
```

A numeric argument to C-q or C-x 8 ... specifies how many copies of the character to insert (see [Numeric Arguments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Arguments.html)).

As an alternative to C-x 8, you can select the corresponding transient input method by typing C-u C-x \ iso-transl RET, then temporarily activating this transient input method by typing C-x \ [ will insert the same character `‘` (see [transient input method](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Input-Method.html#transient-input-method)).

In addition, in some contexts, if you type a quotation using grave accent and apostrophe `like this', it is converted to a form `‘like this’`using single quotation marks, even without C-x 8 commands.  Similarly, typing a quotation ``like this'' using double grave accent and apostrophe converts it to a form`“like this”` using double quotation marks. See [Quotation Marks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quotation-Marks.html).

### 9.2 Changing the Location of Point

To do more than insert characters, you have to know how to move point (see [Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/Point.html)). The keyboard commands C-f, C-b, C-n, and C-p move point to the right, left, down, and up, respectively. You can also move point using the _arrow keys_ present on most keyboards: RIGHT, LEFT, DOWN, and UP; however, many Emacs users find that it is slower to use the arrow keys than the control keys, because you need to move your hand to the area of the keyboard where those keys are located.

You can also click the left mouse button to move point to the position clicked. Emacs also provides a variety of additional keyboard commands that move point in more sophisticated ways.

- C-f[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002df)

  Move forward one character (`forward-char`).

- RIGHT[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-RIGHT)

  This command (`right-char`) behaves like C-f, except when point is in a right-to-left paragraph (see [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html)).

- C-b[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002db)

  Move backward one character (`backward-char`).

- LEFT[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-LEFT)

  This command (`left-char`) behaves like C-b, except if the current paragraph is right-to-left (see [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html)).

- C-n[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dn)

- DOWN

  Move down one screen line (`next-line`). This command attempts to keep the horizontal position unchanged, so if you start in the middle of one line, you move to the middle of the next.

- C-p[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dp)

- UP

  Move up one screen line (`previous-line`). This command preserves position within the line, like C-n.

- C-a[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002da)

- Home

  Move to the beginning of the line (`move-beginning-of-line`).

- C-e[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002de)

- End

  Move to the end of the line (`move-end-of-line`).

- M-f

  Move forward one word (`forward-word`). See [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html).

- C-RIGHT[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dRIGHT)

- M-RIGHT

  This command (`right-word`) behaves like M-f, except it moves _backward_ by one word if the current paragraph is right-to-left. See [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html).

- M-b

  Move backward one word (`backward-word`). See [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html).

- C-LEFT[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dLEFT)

- M-LEFT

  This command (`left-word`) behaves like M-b, except it moves _forward_ by one word if the current paragraph is right-to-left. See [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html).

- M-r[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002dr)

  Without moving the text on the screen, reposition point on the left margin of the center-most text line of the window; on subsequent consecutive invocations, move point to the left margin of the top-most line, the bottom-most line, and so forth, in cyclic order (`move-to-window-line-top-bottom`). A numeric argument says which screen line to place point on, counting downward from the top of the window (zero means the top line). A negative argument counts lines up from the bottom (−1 means the bottom line). See [Numeric Arguments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Arguments.html), for more information on numeric arguments.

- M-<[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002d_003c)

  Move to the top of the buffer (`beginning-of-buffer`). With numeric argument n, move to n/10 of the way from the top. On graphical displays, C-HOME does the same.

- M->[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002d_003e)

  Move to the end of the buffer (`end-of-buffer`). On graphical displays, C-END does the same.

- C-v[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dv)

- PageDown

- next

  Scroll the display one screen forward, and move point onscreen if necessary (`scroll-up-command`). See [Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scrolling.html).

- M-v[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002dv)

- PageUp

- prior

  Scroll one screen backward, and move point onscreen if necessary (`scroll-down-command`). See [Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scrolling.html).

- M-g c[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002dg-c)

  Read a number n and move point to buffer position n. Position 1 is the beginning of the buffer. If point is on or just after a number in the buffer, that is the default for n. Just type RET in the minibuffer to use it. You can also specify n by giving M-g c a numeric prefix argument.

- M-g M-g[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002dg-M_002dg)

- M-g g

  Read a number n and move point to the beginning of line number n (`goto-line`). Line 1 is the beginning of the buffer. If point is on or just after a number in the buffer, that is the default for n. Just type RET in the minibuffer to use it. You can also specify n by giving M-g M-g a numeric prefix argument. See [Creating and Selecting Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Buffer.html), for the behavior of M-g M-g when you give it a plain prefix argument. Alternatively, you can use the command `goto-line-relative` to move point to the line relative to the accessible portion of the narrowed buffer. `goto-line` has its own history list (see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html)). You can have either a single list shared between all buffers (the default) or a separate list for each buffer, by customizing the user option `goto-line-history-local`.

- M-g TAB[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-M_002dg-TAB)

  Read a number n and move to column n in the current line. Column 0 is the leftmost column. If called with a prefix argument, move to the column number specified by the argument’s numeric value.

- C-x C-n[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html#index-C_002dx-C_002dn)

  Use the current column of point as the _semipermanent goal column_ (`set-goal-column`) in the current buffer. When a semipermanent goal column is in effect, C-n, C-p, <prior> and <next> always try to move to this column, or as close as possible to it, after moving vertically. The goal column remains in effect until canceled.

- C-u C-x C-n

  Cancel the goal column. Henceforth, C-n and C-p try to preserve the horizontal position, as usual.

When a line of text in the buffer is longer than the width of the window, Emacs usually displays it on two or more _screen lines_, a.k.a. _visual lines_. For convenience, C-n and C-p move point by screen lines, as do the equivalent keys down and up. You can force these commands to move according to _logical lines_ (i.e., according to the text lines in the buffer) by setting the variable `line-move-visual` to `nil`; if a logical line occupies multiple screen lines, the cursor then skips over the additional screen lines. For details, see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html). See [Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Variables.html), for how to set variables such as `line-move-visual`.

Unlike C-n and C-p, most of the Emacs commands that work on lines work on _logical_ lines. For instance, C-a (`move-beginning-of-line`) and C-e (`move-end-of-line`) respectively move to the beginning and end of the logical line. Whenever we encounter commands that work on screen lines, such as C-n and C-p, we will point these out.

When `line-move-visual` is `nil`, you can also set the variable `track-eol` to a non-`nil` value. Then C-n and C-p, when starting at the end of the logical line, move to the end of the next logical line. Normally, `track-eol` is `nil`.

C-n normally stops at the end of the buffer when you use it on the last line in the buffer. However, if you set the variable `next-line-add-newlines` to a non-`nil` value, C-n on the last line of a buffer creates an additional line at the end and moves down into it.

### 9.3 Erasing Text

- DEL

- BACKSPACE

  Delete the character before point, or the region if it is active (`delete-backward-char`).

- Delete

  Delete the character or grapheme cluster after point, or the region if it is active (`delete-forward-char`).

- C-d

  Delete the character after point (`delete-char`).

- C-k

  Kill to the end of the line (`kill-line`).

- M-d

  Kill forward to the end of the next word (`kill-word`).

- M-DEL

- M-BACKSPACE

  Kill back to the beginning of the previous word (`backward-kill-word`).

The DEL (`delete-backward-char`) command removes the character before point, moving the cursor and the characters after it backwards. If point was at the beginning of a line, this deletes the preceding newline, joining this line to the previous one.

If, however, the region is active, DEL instead deletes the text in the region. See [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html), for a description of the region.

On most keyboards, DEL is labeled BACKSPACE, but we refer to it as DEL in this manual. (Do not confuse DEL with the Delete key; we will discuss Delete momentarily.) On some text terminals, Emacs may not recognize the DEL key properly. See [If DEL Fails to Delete](https://www.gnu.org/software/emacs/manual/html_node/emacs/DEL-Does-Not-Delete.html), if you encounter this problem.

The Delete (`delete-forward-char`) command deletes in the opposite direction: it deletes the character after point, i.e., the character under the cursor. If point was at the end of a line, this joins the following line onto this one. Like DEL, it deletes the text in the region if the region is active (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)). If the character after point is composed with following characters and displayed as a single display unit, a so-called _grapheme cluster_ representing the entire sequence, Delete deletes the entire sequence in one go. This is in contrast to DEL which always deletes a single character, even if the character is composed.

C-d (`delete-char`) deletes the character after point, similar to Delete, but regardless of whether the region is active.

See [Deletion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Deletion.html), for more detailed information about the above deletion commands.

C-k (`kill-line`) erases (kills) a line at a time. If you type C-k at the beginning or middle of a line, it kills all the text up to the end of the line. If you type C-k at the end of a line, it joins that line with the following line.

See [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html), for more information about C-k and related commands.

### 9.4 Undoing Changes

- C-/

- C-x u

- C-\_

  Undo one entry of the undo records—usually, one command worth (`undo`). (The first key might be unavailable on text-mode displays.)

Emacs records a list of changes made in the buffer text, so you can undo recent changes. This is done using the `undo` command, which is bound to C-/ (as well as C-x u and C-\_). Normally, this command undoes the last change, moving point back to where it was before the change. The undo command applies only to changes in the buffer; you can’t use it to undo cursor motion.

On a terminal that supports the Control modifier on all other keys, the easiest way to invoke `undo` is with C-/, since that doesn’t need the Shift modifier. On terminals which allow only the ASCII control characters, C-/ does not exist, but for many of them C-/ still works because it actually sends C-_ to Emacs, while many others allow you to omit the Shift modifier when you type C-_ (in effect pressing C--), making that the most convenient way to invoke `undo`.

Although each editing command usually makes a separate entry in the undo records, very simple commands may be grouped together. Sometimes, an entry may cover just part of a complex command.

If you repeat C-/ (or its aliases), each repetition undoes another, earlier change, back to the limit of the undo information available. If all recorded changes have already been undone, the undo command displays an error message and does nothing.

To learn more about the `undo` command, see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html).

### 9.5 Files

Text that you insert in an Emacs buffer lasts only as long as the Emacs session. To keep any text permanently, you must put it in a _file_.

Suppose there is a file named test.emacs in your home directory. To begin editing this file in Emacs, type

```
C-x C-f test.emacs RET
```

Here the file name is given as an _argument_ to the command C-x C-f (`find-file`). That command uses the _minibuffer_ to read the argument, and you type RET to terminate the argument (see [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html)).

Emacs obeys this command by _visiting_ the file: it creates a buffer, copies the contents of the file into the buffer, and then displays the buffer for editing. If you alter the text, you can _save_ the new text in the file by typing C-x C-s (`save-buffer`). This copies the altered buffer contents back into the file test.emacs, making them permanent. Until you save, the changed text exists only inside Emacs, and the file test.emacs is unaltered.

To create a file, just visit it with C-x C-f as if it already existed. This creates an empty buffer, in which you can insert the text you want to put in the file. Emacs actually creates the file the first time you save this buffer with C-x C-s.

To learn more about using files in Emacs, see [File Handling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Files.html).



### 9.6 Help



If you forget what a key does, you can find out by typing C-h k (`describe-key`), followed by the key of interest; for example, C-h k C-n tells you what C-n does.

The prefix key C-h stands for “help”.  The key F1 serves as an alias for C-h.  Apart from C-h k, there are many other help commands providing different kinds of help.

See [Help](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help.html), for details.



### 9.7 Blank Lines



Here are special commands and techniques for inserting and deleting blank lines.

- C-o

  Insert a blank line after the cursor (`open-line`).

- C-x C-o

  Delete all but one of many  blank lines (`delete-blank-lines`).



We have seen how RET (`newline`) starts a new line of text.  However, it may be easier to see what you are doing if you first make a blank line and then insert the desired text into it. This is easy to do using the key C-o (`open-line`), which inserts a newline after point but leaves point in front of the newline.  After C-o, type the text for the new line.

You can make several blank lines by typing C-o several times, or by giving it a numeric argument specifying how many blank lines to make. See [Numeric Arguments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Arguments.html), for how.  If you have a fill prefix, the C-o command inserts the fill prefix on the new line, if typed at the beginning of a line.  See [The Fill Prefix](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fill-Prefix.html).

The easy way to get rid of extra blank lines is with the command C-x C-o (`delete-blank-lines`).  If point lies within a run of several blank lines, C-x C-o deletes all but one of them.  If point is on a single blank line, C-x C-o deletes it.  If point is on a nonblank line, C-x C-o deletes all following blank lines, if any exists.



### 9.8 Continuation Lines

Sometimes, a line of text in the buffer—a *logical line*—is too long to fit in the window, and Emacs displays it as two or more *screen lines*, or *visual lines*.  This is called *line wrapping* or *continuation*, and the long logical line is called a *continued line*.  On a graphical display, Emacs indicates line wrapping with small bent arrows in the left and right window fringes. On a text terminal, Emacs indicates line wrapping by displaying a ‘\’ character at the right margin.

Most commands that act on lines act on logical lines, not screen lines.  For instance, C-k kills a logical line.  As described earlier, C-n (`next-line`) and C-p (`previous-line`) are special exceptions: they move point down and up, respectively, by one screen line (see [Changing the Location of Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html)).



Emacs can optionally *truncate* long logical lines instead of continuing them.  This means that every logical line occupies a single screen line; if it is longer than the width of the window, the rest of the line is not displayed.  On a graphical display, a truncated line is indicated by a small straight arrow in the right fringe; on a text terminal, it is indicated by a ‘$’ character in the right margin. See [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html).

By default, continued lines are wrapped at the right window edge. Since the wrapping may occur in the middle of a word, continued lines can be difficult to read.  The usual solution is to break your lines before they get too long, by inserting newlines.  If you prefer, you can make Emacs insert a newline automatically when a line gets too long, by using Auto Fill mode.  See [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html).



Normally, the first character of each continuation line is positioned at the beginning of the screen line where it is displayed. The minor mode `visual-wrap-prefix-mode` and its global counterpart (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)) `global-visual-wrap-prefix-mode` arranges for continuation lines to be indented on display using a fill prefix (see [The Fill Prefix](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fill-Prefix.html)) automatically computed from each line’s surrounding context.  These prefixes are display-only feature, and do not change the buffer text in any way.

Sometimes, you may need to edit files containing many long logical lines, and it may not be practical to break them all up by adding newlines.  In that case, you can use Visual Line mode, which enables *word wrapping*: instead of wrapping long lines exactly at the right window edge, Emacs wraps them at the word boundaries (i.e., space or tab characters) nearest to the right window edge.  Visual Line mode also redefines editing commands such as C-a, C-n, and C-k to operate on screen lines rather than logical lines.  See [Visual Line Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visual-Line-Mode.html).



### 9.8 Continuation Lines



Sometimes, a line of text in the buffer—a *logical line*—is too long to fit in the window, and Emacs displays it as two or more *screen lines*, or *visual lines*.  This is called *line wrapping* or *continuation*, and the long logical line is called a *continued line*.  On a graphical display, Emacs indicates line wrapping with small bent arrows in the left and right window fringes. On a text terminal, Emacs indicates line wrapping by displaying a ‘\’ character at the right margin.

Most commands that act on lines act on logical lines, not screen lines.  For instance, C-k kills a logical line.  As described earlier, C-n (`next-line`) and C-p (`previous-line`) are special exceptions: they move point down and up, respectively, by one screen line (see [Changing the Location of Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html)).



Emacs can optionally *truncate* long logical lines instead of continuing them.  This means that every logical line occupies a single screen line; if it is longer than the width of the window, the rest of the line is not displayed.  On a graphical display, a truncated line is indicated by a small straight arrow in the right fringe; on a text terminal, it is indicated by a ‘$’ character in the right margin. See [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html).

By default, continued lines are wrapped at the right window edge. Since the wrapping may occur in the middle of a word, continued lines can be difficult to read.  The usual solution is to break your lines before they get too long, by inserting newlines.  If you prefer, you can make Emacs insert a newline automatically when a line gets too long, by using Auto Fill mode.  See [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html).



Normally, the first character of each continuation line is positioned at the beginning of the screen line where it is displayed. The minor mode `visual-wrap-prefix-mode` and its global counterpart (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)) `global-visual-wrap-prefix-mode` arranges for continuation lines to be indented on display using a fill prefix (see [The Fill Prefix](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fill-Prefix.html)) automatically computed from each line’s surrounding context.  These prefixes are display-only feature, and do not change the buffer text in any way.

Sometimes, you may need to edit files containing many long logical lines, and it may not be practical to break them all up by adding newlines.  In that case, you can use Visual Line mode, which enables *word wrapping*: instead of wrapping long lines exactly at the right window edge, Emacs wraps them at the word boundaries (i.e., space or tab characters) nearest to the right window edge.  Visual Line mode also redefines editing commands such as C-a, C-n, and C-k to operate on screen lines rather than logical lines.  See [Visual Line Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visual-Line-Mode.html).



### 9.9 Cursor Position Information

Here are commands to get information about the size and position of parts of the buffer, and to count words and lines.

- M-x what-line

  Display the line number of point.

- M-x line-number-mode

- M-x column-number-mode

  Toggle automatic display of the current line number or column number. See [Optional Mode Line Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Optional-Mode-Line.html).  If you want to have a line number displayed before each line, see [Customization of Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display-Custom.html).

- M-=

  Display the number of lines, sentences, words, and characters that are present in the region (`count-words-region`).  See [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html), for information about the region.

- M-x count-words

  Display the number of lines, sentences, words, and characters that are present in the buffer.  If the region is active (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)), display the numbers for the region instead.

- C-x =

  Display the character code of character after point, character position of point, and column of point (`what-cursor-position`).

- M-x hl-line-mode

  Enable or disable highlighting of the current line.  See [Displaying the Cursor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cursor-Display.html).

- M-x size-indication-mode

  Toggle automatic display of the size of the buffer. See [Optional Mode Line Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Optional-Mode-Line.html).



M-x what-line displays the current line number in the echo area.  This command is usually redundant because the current line number is shown in the mode line (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)).  However, if you narrow the buffer, the mode line shows the line number relative to the accessible portion (see [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html)).  By contrast, `what-line` displays both the line number relative to the narrowed region and the line number relative to the whole buffer.



M-= (`count-words-region`) displays a message reporting the number of lines, sentences, words, and characters in the region (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html), for an explanation of the region).  With a prefix argument, C-u M-=, the command displays a count for the entire buffer.



The command M-x count-words does the same job, but with a different calling convention.  It displays a count for the region if the region is active, and for the buffer otherwise.



The command C-x = (`what-cursor-position`) shows information about the current cursor position and the buffer contents at that position.  It displays a line in the echo area that looks like this:

```
Char: c (99, #o143, #x63) point=28062 of 36168 (78%) column=53
```

After ‘Char:’, this shows the character in the buffer at point. The text inside the parenthesis shows the corresponding decimal, octal and hex character codes; for more information about how C-x = displays character information, see [Introduction to International Character Sets](https://www.gnu.org/software/emacs/manual/html_node/emacs/International-Chars.html).  After ‘point=’ is the position of point as a character count (the first character in the buffer is position 1, the second character is position 2, and so on).  The number after that is the total number of characters in the buffer, and the number in parenthesis expresses the position as a percentage of the total.  After ‘column=’ is the horizontal position of point, in columns counting from the left edge of the window.



If the user option `what-cursor-show-names` is non-`nil`, the name of the character, as defined by the Unicode Character Database, is shown as well.  The part in parentheses would then become:

```
(99, #o143, #x63, LATIN SMALL LETTER C)
```

If the buffer has been narrowed, making some of the text at the beginning and the end temporarily inaccessible, C-x = displays additional text describing the currently accessible range.  For example, it might display this:

```
Char: C (67, #o103, #x43) point=252 of 889 (28%) <231-599> column=0
```

where the two extra numbers give the smallest and largest character position that point is allowed to assume.  The characters between those two positions are the accessible ones.  See [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html).

Related, but different feature is `display-line-numbers-mode` (see [Customization of Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display-Custom.html)).



### 9.10 Numeric Arguments



In the terminology of mathematics and computing, *argument* means “data provided to a function or operation”.  You can give any Emacs command a *numeric argument* (also called a *prefix argument*).  Some commands interpret the argument as a repetition count.  For example, giving C-f an argument of ten causes it to move point forward by ten characters instead of one.  With these commands, no argument is equivalent to an argument of one, and negative arguments cause them to move or act in the opposite direction.



The easiest way to specify a numeric argument is to type a digit and/or a minus sign while holding down the Meta key.  For example,

```
M-5 C-n
```

moves down five lines.  The keys M-1, M-2, and so on, as well as M--, are bound to commands (`digit-argument` and `negative-argument`) that set up an argument for the next command.  M-- without digits normally means −1.

If you enter more than one digit, you need not hold down the Meta key for the second and subsequent digits.  Thus, to move down fifty lines, type

```
M-5 0 C-n
```

Note that this *does not* insert five copies of ‘0’ and move down one line, as you might expect—the ‘0’ is treated as part of the prefix argument.

(What if you do want to insert five copies of ‘0’?  Type M-5 C-u 0.  Here, C-u terminates the prefix argument, so that the next keystroke begins the command that you want to execute.  Note that this meaning of C-u applies only to this case.  For the usual role of C-u, see below.)



Instead of typing M-1, M-2, and so on, another way to specify a numeric argument is to type C-u (`universal-argument`) followed by some digits, or (for a negative argument) a minus sign followed by digits.  A minus sign without digits normally means −1.

C-u alone has the special meaning of “four times”: it multiplies the argument for the next command by four.  C-u C-u multiplies it by sixteen.  Thus, C-u C-u C-f moves forward sixteen characters.  Other useful combinations are C-u C-n, C-u C-u C-n (move down a good fraction of a screen), C-u C-u C-o (make sixteen blank lines), and C-u C-k (kill four lines).

You can use a numeric argument before a self-inserting character to insert multiple copies of it.  This is straightforward when the character is not a digit; for example, C-u 6 4 a inserts 64 copies of the character ‘a’.  But this does not work for inserting digits; C-u 6 4 1 specifies an argument of 641. You can separate the argument from the digit to insert with another C-u; for example, C-u 6 4 C-u 1 does insert 64 copies of the character ‘1’.

Some commands care whether there is an argument, but ignore its value.  For example, the command M-q (`fill-paragraph`) fills text; with an argument, it justifies the text as well. (See [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html), for more information on M-q.)  For these commands, it is enough to specify the argument with a single C-u.

Some commands use the value of the argument as a repeat count but do something special when there is no argument.  For example, the command C-k (`kill-line`) with argument n kills n lines, including their terminating newlines.  But C-k with no argument is special: it kills the text up to the next newline, or, if point is right at the end of the line, it kills the newline itself.  Thus, two C-k commands with no arguments can kill a nonblank line, just like C-k with an argument of one. (See [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html), for more information on C-k.)

A few commands treat a plain C-u differently from an ordinary argument.  A few others may treat an argument of just a minus sign differently from an argument of −1.  These unusual cases are described when they come up; they exist to make an individual command more convenient, and they are documented in that command’s documentation string.

We use the term *prefix argument* to emphasize that you type such arguments *before* the command, and to distinguish them from minibuffer arguments (see [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html)), which are entered *after* invoking the command.

On graphical displays, C-0, C-1, etc. act the same as M-0, M-1, etc.



### 9.11 Repeating a Command



Many simple commands, such as those invoked with a single key or with M-x command-name RET, can be repeated by invoking them with a numeric argument that serves as a repeat count (see [Numeric Arguments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Arguments.html)).  However, if the command you want to repeat prompts for input, or uses a numeric argument in another way, that method won’t work.



The command C-x z (`repeat`) provides another way to repeat an Emacs command many times.  This command repeats the previous Emacs command, whatever that was.  Repeating a command uses the same arguments that were used before; it does not read new arguments each time.

To repeat the command more than once, type additional z’s: each z repeats the command one more time.  Repetition ends when you type a character other than z or press a mouse button.

For example, suppose you type C-u 2 0 C-d to delete 20 characters.  You can repeat that command (including its argument) three additional times, to delete a total of 80 characters, by typing C-x z z z.  The first C-x z repeats the command once, and each subsequent z repeats it once again.



You can also activate `repeat-mode` which allows repeating commands bound to sequences of two or more keys by typing a single character.  For example, after typing C-x u (`undo`, see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html)) to undo the most recent edits, you can undo many more edits by typing u u u….  Similarly, type C-x o o o… instead of C-x o C-x o C-x o… to switch to the window several windows away.  This works by entering a transient repeating mode after you type the full key sequence that invokes the command; the single-key shortcuts are shown in the echo area.

Only some commands support repetition in `repeat-mode`; type M-x describe-repeat-maps RET to see which ones.

The single-character shortcuts enabled by the transient repeating mode do not need to be identical: for example, after typing C-x {, either { or } or ^ or v, or any series that mixes these characters in any order, will resize the selected window in respective ways.  Similarly, after M-g n or M-g p, typing any sequence of n and/or p in any mix will repeat `next-error` and `previous-error` to navigate in a *compilation* or *grep* buffer (see [Compilation Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compilation-Mode.html)).

Typing any key other than those defined to repeat the previous command exits the transient repeating mode, and then the key you typed is executed normally.  You can also define a key which will exit the transient repeating mode *without* executing the key which caused the exit.  To this end, customize the user option `repeat-exit-key` to name a key; one natural value is RET. Finally, it’s possible to break the repetition chain automatically after some amount of idle time: customize the user option `repeat-exit-timeout` to specify the idle time in seconds after which this transient repetition mode will be turned off automatically.

## 10 The Minibuffer

The *minibuffer* is where Emacs commands read complicated arguments, such as file names, buffer names, Emacs command names, or Lisp expressions.  We call it the “minibuffer” because it’s a special-purpose buffer with a small amount of screen space.  You can use the usual Emacs editing commands in the minibuffer to edit the argument text.



### 10.1 Using the Minibuffer



When the minibuffer is in use, it appears in the echo area, with a cursor.  The minibuffer starts with a *prompt*, usually ending with a colon.  The prompt states what kind of input is expected, and how it will be used.  The prompt is highlighted using the `minibuffer-prompt` face (see [Text Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Faces.html)).

The simplest way to enter a minibuffer argument is to type the text, then RET to submit the argument and exit the minibuffer. Alternatively, you can type C-g to exit the minibuffer by canceling the command asking for the argument (see [Quitting and Aborting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quitting.html)).



Sometimes, the prompt shows a *default argument*, inside parentheses before the colon.  This default will be used as the argument if you just type RET.  For example, commands that read buffer names usually show a buffer name as the default; you can type RET to operate on that default buffer.  You can customize how the default argument is shown with the user option `minibuffer-default-prompt-format`.



If you enable Minibuffer Electric Default mode, a global minor mode, Emacs hides the default argument as soon as you modify the contents of the minibuffer (since typing RET would no longer submit that default).  If you ever bring back the original minibuffer text, the prompt again shows the default.  To enable this minor mode, type M-x minibuffer-electric-default-mode.

Since the minibuffer appears in the echo area, it can conflict with other uses of the echo area.  If an error message or an informative message is emitted while the minibuffer is active, the message is displayed in brackets after the minibuffer text for a few seconds, or until you type something; then the message disappears.  While the minibuffer is in use, Emacs does not echo keystrokes.



While using the minibuffer, you can switch to a different frame, perhaps to note text you need to enter (see [Frame Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frame-Commands.html)).  By default, the active minibuffer moves to this new frame.  If you set the user option `minibuffer-follows-selected-frame` to `nil`, then the minibuffer stays in the frame where you opened it, and you must switch back to that frame in order to complete (or abort) the current command.  If you set that option to a value which is neither `nil` nor `t`, the minibuffer moves frame only after a recursive minibuffer has been opened in the current command (see [(elisp)Recursive Mini](https://www.gnu.org/software/emacs/manual/html_node/elisp/Recursive-Mini.html#Recursive-Mini)).  This option is mainly to retain (approximately) the behavior prior to Emacs 28.1.  Note that the effect of the command, when you finally finish using the minibuffer, always takes place in the frame where you first opened it.  The sole exception is that when that frame no longer exists, the action takes place in the currently selected frame.



### 10.2 Minibuffers for File Names



Commands such as C-x C-f (`find-file`) use the minibuffer to read a file name argument (see [Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Basic-Files.html)).  When the minibuffer is used to read a file name, it typically starts out with some initial text ending in a slash.  This is the *default directory*.  For example, it may start out like this:

```
Find file: /u2/emacs/src/
```

Here, ‘Find file: ’ is the prompt and ‘/u2/emacs/src/’ is the default directory.  If you now type buffer.c as input, that specifies the file /u2/emacs/src/buffer.c.  See [File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Names.html), for information about the default directory.

Alternative defaults for the file name you may want are available by typing M-n, see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html).

You can specify a file in the parent directory with ..: /a/b/../foo.el is equivalent to /a/foo.el. Alternatively, you can use M-DEL to kill directory names backwards (see [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html)).

To specify a file in a completely different directory, you can kill the entire default with C-a C-k (see [Editing in the Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-Edit.html)). Alternatively, you can ignore the default, and enter an absolute file name starting with a slash or a tilde after the default directory. For example, you can specify /etc/termcap as follows:

```
Find file: /u2/emacs/src//etc/termcap
```



A double slash causes Emacs to ignore everything before the second slash in the pair.  In the example above, /u2/emacs/src/ is ignored, so the argument you supplied is /etc/termcap.  The ignored part of the file name is dimmed if the terminal allows it.  (To disable this dimming, turn off File Name Shadow mode with the command M-x file-name-shadow-mode.)

When completing remote file names (see [Remote Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Remote-Files.html)), a double slash behaves slightly differently: it causes Emacs to ignore only the file-name part, leaving the rest (method, host and username, etc.) intact.  Typing three slashes in a row ignores everything in remote file names.  See [File name completion](https://www.gnu.org/software/emacs/manual/html_node/tramp/File-name-completion.html#File-name-completion) in The Tramp Manual.



Emacs interprets ~/ as your home directory.  Thus, ~/foo/bar.txt specifies a file named bar.txt, inside a directory named foo, which is in turn located in your home directory.  In addition, ~user-id/ means the home directory of a user whose login name is user-id.  Any leading directory name in front of the ~ is ignored: thus, /u2/emacs/~/foo/bar.txt is equivalent to ~/foo/bar.txt.

On MS-Windows and MS-DOS systems, where a user doesn’t always have a home directory, Emacs uses several alternatives.  For MS-Windows, see [HOME and Startup Directories on MS-Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows-HOME.html); for MS-DOS, see [File Names on MS-DOS](https://www.gnu.org/software/emacs/manual/html_node/emacs/MS_002dDOS-File-Names.html). On these systems, the ~user-id/ construct is supported only for the current user, i.e., only if user-id is the current user’s login name.



To prevent Emacs from inserting the default directory when reading file names, change the variable `insert-default-directory` to `nil`.  In that case, the minibuffer starts out empty. Nonetheless, relative file name arguments are still interpreted based on the same default directory.

You can also enter remote file names in the minibuffer. See [Remote Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Remote-Files.html).



### 10.3 Editing in the Minibuffer

The minibuffer is an Emacs buffer, albeit a peculiar one, and the usual Emacs commands are available for editing the argument text. (The prompt, however, is *read-only*, and cannot be changed.)

Since RET in the minibuffer submits the argument, you can’t use it to insert a newline.  You can do that with C-q C-j, which inserts a C-j control character, which is formally equivalent to a newline character (see [Inserting Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Inserting-Text.html)).  Alternatively, you can use the C-o (`open-line`) command (see [Blank Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Blank-Lines.html)).

Inside a minibuffer, the keys TAB, SPC, and ? are often bound to *completion commands*, which allow you to easily fill in the desired text without typing all of it.  See [Completion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion.html). As with RET, you can use C-q to insert a TAB, SPC, or ‘?’ character.  If you want to make SPC and ? insert normally instead of starting completion, you can put the following in your init file:

```
(keymap-unset minibuffer-local-completion-map "SPC")
(keymap-unset minibuffer-local-completion-map "?")
```

For convenience, C-a (`move-beginning-of-line`) in a minibuffer moves point to the beginning of the argument text, not the beginning of the prompt.  For example, this allows you to erase the entire argument with C-a C-k.



When the minibuffer is active, the echo area is treated much like an ordinary Emacs window.  For instance, you can switch to another window (with C-x o), edit text there, then return to the minibuffer window to finish the argument.  You can even kill text in another window, return to the minibuffer window, and yank the text into the argument.  There are some restrictions on the minibuffer window, however: for instance, you cannot split it.  See [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html).



Normally, the minibuffer window occupies a single screen line. However, if you add two or more lines’ worth of text into the minibuffer, it expands automatically to accommodate the text.  The variable `resize-mini-windows` controls the resizing of the minibuffer.  The default value is `grow-only`, which means the behavior we have just described.  If the value is `t`, the minibuffer window will also shrink automatically if you remove some lines of text from the minibuffer, down to a minimum of one screen line.  If the value is `nil`, the minibuffer window never changes size automatically, but you can use the usual window-resizing commands on it (see [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html)).



The variable `max-mini-window-height` controls the maximum height for resizing the minibuffer window.  A floating-point number specifies a fraction of the frame’s height; an integer specifies the maximum number of lines; `nil` means do not resize the minibuffer window automatically.  The default value is 0.25.

The C-M-v command in the minibuffer scrolls the help text from commands that display help text of any sort in another window.  You can also scroll the help text with M-PageUp and M-PageDown (or, equivalently, M-prior and M-next).  This is especially useful with long lists of possible completions.  See [Using Other Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Other-Window.html).



Emacs normally disallows most commands that use the minibuffer while the minibuffer is active.  To allow such commands in the minibuffer, set the variable `enable-recursive-minibuffers` to `t`. You might need also to enable `minibuffer-depth-indicate-mode` to show the current recursion depth in the minibuffer prompt on recursive use of the minibuffer.

When active, the minibuffer is usually in `minibuffer-mode`. This is an internal Emacs mode without any special features.

When not active, the minibuffer is in `minibuffer-inactive-mode`, and clicking mouse-1 there shows the *Messages* buffer. If you use a dedicated frame for minibuffers, Emacs also recognizes certain keys there, for example, n to make a new frame.

### 10.4 Completion

You can often use a feature called *completion* to help enter arguments.  This means that after you type part of the argument, Emacs can fill in the rest, or some of it, based on what was typed so far.

When completion is available, certain keys (usually TAB, RET, and SPC) are rebound in the minibuffer to special completion commands (see [Completion Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Commands.html)).  These commands attempt to complete the text in the minibuffer, based on a set of *completion alternatives* provided by the command that requested the argument.  You can usually type ? to see a list of completion alternatives.

Although completion is usually done in the minibuffer, the feature is sometimes available in ordinary buffers too.  See [Completion for Symbol Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Symbol-Completion.html).



#### 10.4.1 Completion Example



A simple example may help here.  M-x uses the minibuffer to read the name of a command, so completion works by matching the minibuffer text against the names of existing Emacs commands.  Suppose you wish to run the command `auto-fill-mode`.  You can do that by typing M-x auto-fill-mode RET, but it is easier to use completion.

If you type M-x a u TAB, the TAB looks for completion alternatives (in this case, command names) that start with ‘au’.  There are several, including `auto-fill-mode` and `autoconf-mode`, but they all begin with `auto`, so the ‘au’ in the minibuffer completes to ‘auto’.  (More commands may be defined in your Emacs session.  For example, if a command called `authorize-me` was defined, Emacs could only complete as far as ‘aut’.)

If you type TAB again immediately, it cannot determine the next character; it could be ‘-’, ‘a’, or ‘c’.  So it does not add any characters; instead, TAB displays a list of all possible completions in another window.

Next, type -f.  The minibuffer now contains ‘auto-f’, and the only command name that starts with this is `auto-fill-mode`. If you now type TAB, completion fills in the rest of the argument ‘auto-fill-mode’ into the minibuffer.

Hence, typing just a u TAB - f TAB allows you to enter ‘auto-fill-mode’.

TAB also works while point is not at the end of the minibuffer.  In that case, it will fill in text both at point and at the end of the minibuffer.  If you type M-x autocm, then press C-b to move point before the ‘m’, you can type TAB to insert the text ‘onf-’ at point and ‘ode’ at the end of the minibuffer, so that the minibuffer contains ‘autoconf-mode’.



#### 10.4.2 Completion Commands

Here is a list of the completion commands defined in the minibuffer when completion is allowed.

- TAB

  Complete the text in the minibuffer as much as possible; if unable to complete, display a list of possible completions.

- SPC

  Complete up to one word from the minibuffer text before point.

- C-x UP

  Complete the text in the minibuffer using minibuffer history.

- C-x DOWN

  Complete the text in the minibuffer using minibuffer defaults.

- RET

  Submit the text in the minibuffer as the argument, possibly completing first.  See [Completion Exit](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Exit.html).

- ?

  Display a list of completions and a few useful key bindings (`minibuffer-completion-help`).

- M-DOWN

- M-UP

  Navigate through list of completions.

- M-v

- M-g M-c

- PageUp

- prior

  While in the minibuffer, select the window showing the completion list.

- RET

  In the completions buffer, choose the completion at point.

- mouse-1

- mouse-2

  In the completions buffer, choose the completion at mouse click.

- TAB

- RIGHT

- n

  In the completions buffer, move to the following completion candidate.

- S-TAB

- LEFT

- p

  In the completions buffer, move to the previous completion candidate.

- q

  Quit the completions window and switch to the minibuffer window.

- z

  Kill the completions buffer and delete the window showing it.



TAB (`minibuffer-complete`) is the most fundamental completion command.  It searches for all possible completions that match the existing minibuffer text, and attempts to complete as much as it can.  See [How Completion Alternatives Are Chosen](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html), for how completion alternatives are chosen.



SPC (`minibuffer-complete-word`) completes like TAB, but only up to the next hyphen or space.  If you have ‘auto-f’ in the minibuffer and type SPC, it finds that the completion is ‘auto-fill-mode’, but it only inserts ‘ill-’, giving ‘auto-fill-’.  Another SPC at this point completes all the way to ‘auto-fill-mode’.  This command is not available for arguments that often include spaces, such as file names.



If TAB or SPC is unable to complete, it displays in another window a list of matching completion alternatives (if there are any) and a few useful commands to select a completion candidate.  You can display the same completion list and help with ? (`minibuffer-completion-help`).  The following commands can be used with the completion list:



While in the minibuffer or in the completion list buffer, M-DOWN (`minibuffer-next-completion` and M-UP (`minibuffer-previous-completion`) navigate through the completions displayed in the completions buffer.  When `minibuffer-completion-auto-choose` is non-`nil` (which is the default), using these commands also inserts the current completion candidate into the minibuffer.  If `minibuffer-completion-auto-choose` is `nil`, you can use the M-RET command (`minibuffer-choose-completion`) to insert the completion candidates into the minibuffer.  By default, that exits the minibuffer, but with a prefix argument, C-u M-RET inserts the currently active candidate without exiting the minibuffer.



Typing M-v, while in the minibuffer, selects the window showing the completion list (`switch-to-completions`).  This paves the way for using the commands below.  PageUp, prior and M-g M-c do the same.  You can also select the window in other ways (see [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html)).



While in the completion list buffer, RET chooses the completion candidate at point (`choose-completion`) and mouse-1 and mouse-2 choose the completion at mouse click.  With a prefix argument, C-u RET inserts the completion at point into the minibuffer, but doesn’t exit the minibuffer—thus, you can change your mind and choose another candidate.



While in the completion list buffer, you can use TAB, RIGHT, or n to move point to the following completion candidate (`next-completion`).  You can also use S-TAB, LEFT, and p to move point to the previous completion alternative (`previous-completion`).



You can also complete using the history of minibuffer inputs for the command which prompted you.  C-x UP (`minibuffer-complete-history`) works like TAB, but completes using minibuffer history instead of the usual completion candidates.  A similar command C-x DOWN (`minibuffer-complete-defaults`) completes using the default input items provided by the prompting command.



Finally, q quits the window showing it and selects the window showing the minibuffer (`quit-window`), and z kills the completion buffer and delete the window showing it (`kill-current-buffer`).



If the variable `minibuffer-visible-completions` is customized to a non-`nil` value, it changes the commands bound to the arrow keys: instead of moving in the minibuffer, they move between completion candidates, like meta-arrow keys do by default.  Similarly, RET selects the current candidate, like M-RET does normally.  `C-g` hides the completion window, but leaves the minibuffer active, so you can continue typing at the prompt.

#### 10.4.3 Completion Exit

When a command reads an argument using the minibuffer with completion, it also controls what happens when you type RET (`minibuffer-complete-and-exit`) to submit the argument.  There are four types of behavior:

**Strict completion** accepts only exact completion matches. Typing RET exits the minibuffer only if the minibuffer text is an exact match, or completes to one. Otherwise, Emacs refuses to exit the minibuffer; instead it tries to complete, and if no completion can be done it momentarily displays ‘[No match]’ after the minibuffer text. (You can still leave the minibuffer by typing C-g to cancel the command.)

An example of a command that uses this behavior is M-x, since it is meaningless for it to accept a non-existent command name.

**Cautious completion** is like strict completion, except RET exits only if the text is already an exact match. If the text completes to an exact match, RET performs that completion but does not exit yet; you must type a second RET to exit.

**Cautious completion** is used for reading file names for files that must already exist, for example.

**Permissive completion** allows any input; the completion candidates are just suggestions. Typing RET does not complete, it just submits the argument as you have entered it.

**Permissive completion with confirmation** is like permissive completion, with an exception: if you typed TAB and this completed the text up to some intermediate state (i.e., one that is not yet an exact completion match), typing RET right afterward does not submit the argument. Instead, Emacs asks for confirmation by momentarily displaying ‘[Confirm]’ after the text; type RET again to confirm and submit the text. This catches a common mistake, in which one types RET before realizing that TAB did not complete as far as desired.

You can tweak the confirmation behavior by customizing the variable `confirm-nonexistent-file-or-buffer`. The default value, `after-completion`, gives the behavior we have just described. If you change it to nil, Emacs does not ask for confirmation, falling back on permissive completion. If you change it to any other non-nil value, Emacs asks for confirmation whether or not the preceding command was TAB.

This behavior is used by most commands that read file names, like C-x C-f, and commands that read buffer names, like C-x b.

#### 10.4.4 How Completion Alternatives Are Chosen

Completion commands work by narrowing a large list of possible completion alternatives to a smaller subset that matches what you have typed in the minibuffer.  In [Completion Example](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Example.html), we gave a simple example of such matching.  The procedure of determining what constitutes a match is quite intricate.  Emacs attempts to offer plausible completions under most circumstances.

Emacs performs completion using one or more *completion styles*—sets of criteria for matching minibuffer text to completion alternatives.  During completion, Emacs tries each completion style in turn.  If a style yields one or more matches, that is used as the list of completion alternatives.  If a style produces no matches, Emacs falls back on the next style.



The list variable `completion-styles` specifies the completion styles to use.  Each list element is the name of a completion style (a Lisp symbol).  The available style symbols are stored in the variable `completion-styles-alist` (see [Completion Variables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Completion-Variables.html#Completion-Variables) in The Emacs Lisp Reference Manual).  The default completion styles are (in order):

- `basic`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html#index-basic_002c-completion-style)

  A matching completion alternative must have the same beginning as the text in the minibuffer before point.  Furthermore, if there is any text in the minibuffer after point, the rest of the completion alternative must contain that text as a substring.

- `partial-completion`

  This aggressive completion style divides the minibuffer text into words separated by hyphens or spaces, and completes each word separately.  (For example, when completing command names, ‘em-l-m’ completes to ‘emacs-lisp-mode’.) Furthermore, a ‘*’ in the minibuffer text is treated as a *wildcard*—it matches any string of characters at the corresponding position in the completion alternative.

- `emacs22`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html#index-emacs22_002c-completion-style)

  This completion style is similar to `basic`, except that it ignores the text in the minibuffer after point.  It is so-named because it corresponds to the completion behavior in Emacs 22.

The following additional completion styles are also defined, and you can add them to `completion-styles` if you wish (see [Customization](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customization.html)):

- `substring`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html#index-substring_002c-completion-style)

  A matching completion alternative must contain the text in the minibuffer before point, and the text in the minibuffer after point, as substrings (in that same order). Thus, if the text in the minibuffer is ‘foobar’, with point between ‘foo’ and ‘bar’, that matches ‘afoobbarc’, where a, b, and c can be any string including the empty string.

- `flex`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html#index-flex_002c-completion-style)

  This aggressive completion style, also known as `flx` or `fuzzy` or `scatter` completion, attempts to complete using in-order substrings.  For example, it can consider ‘foo’ to match ‘frodo’ or ‘fbarbazoo’.

- `initials`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html#index-initials_002c-completion-style)

  This very aggressive completion style attempts to complete acronyms and initialisms.  For example, when completing command names, it matches ‘lch’ to ‘list-command-history’.

There is also a very simple completion style called `emacs21`. In this style, if the text in the minibuffer is ‘foobar’, only matches starting with ‘foobar’ are considered.

You can use different completion styles in different situations, by setting the variable `completion-category-overrides`. For example, the default setting says to use only `basic` and `substring` completion for buffer names.



#### 10.4.5 Completion Options



Case is significant when completing case-sensitive arguments, such as command names.  For example, when completing command names, ‘AU’ does not complete to ‘auto-fill-mode’.  Case differences are ignored when completing arguments in which case does not matter.



When completing file names, case differences are ignored if the variable `read-file-name-completion-ignore-case` is non-`nil`.  The default value is `nil` on systems that have case-sensitive file-names, such as GNU/Linux; it is non-`nil` on systems that have case-insensitive file-names, such as Microsoft Windows.  When completing buffer names, case differences are ignored if the variable `read-buffer-completion-ignore-case` is non-`nil`; the default is `nil`.



When completing file names, Emacs usually omits certain alternatives that are considered unlikely to be chosen, as determined by the list variable `completion-ignored-extensions`.  Each element in the list should be a string; any file name ending in such a string is ignored as a completion alternative.  Any element ending in a slash (/) represents a subdirectory name.  The standard value of `completion-ignored-extensions` has several elements including `".o"`, `".elc"`, and `"~"`.  For example, if a directory contains ‘foo.c’ and ‘foo.elc’, ‘foo’ completes to ‘foo.c’.  However, if *all* possible completions end in otherwise-ignored strings, they are not ignored: in the previous example, ‘foo.e’ completes to ‘foo.elc’.  Emacs disregards `completion-ignored-extensions` when showing completion alternatives in the completion list.

Shell completion is an extended version of filename completion, see [Shell Mode Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shell-Options.html).



If `completion-auto-help` is set to `nil`, the completion commands never display the completion list buffer; you must type ? to display the list.  If the value is `lazy`, Emacs only shows the completion list buffer on the second attempt to complete. In other words, if there is nothing to complete, the first TAB echoes ‘Next char not unique’; the second TAB shows the completion list buffer.  If the value is `always`, the completion list buffer is always shown when completion is attempted.

The display of the completion list buffer after it is shown for the first time is also controlled by `completion-auto-help`.  If the value is `t` or `lazy`, the window showing the completions pops down when Emacs is able to complete (and may pop up again if Emacs is again unable to complete after you type some more text); if the value is `always`, the window pops down only when you exit the completion.  The value `visible` is a hybrid: it behaves like `t` when it decides whether to pop up the window showing the completion list buffer, and like `always` when it decides whether to pop it down.



Emacs can optionally select the window showing the completions when it shows that window.  To enable this behavior, customize the user option `completion-auto-select` to `t`, which changes the behavior of TAB when Emacs pops up the completions: pressing TAB will switch to the completion list buffer, and you can then move to a candidate by cursor motion commands and select it with RET.  If the value of `completion-auto-select` is `second-tab`, then the first TAB will pop up the completions list buffer, and the second one will switch to it.



When the window showing the completions is selected, either because you customized `completion-auto-select` or because you switched to it by typing C-x o, the UP and DOWN arrow keys (`previous-line-completion` and `next-line-completion`, respectively) move by lines between completion candidates; with a prefix numeric argument, they move that many lines.  If `completion-auto-wrap` is non-`nil`, these commands will wrap at bottom and top of the candidate list.



If `completion-cycle-threshold` is non-`nil`, completion commands can cycle through completion alternatives.  Normally, if there is more than one completion alternative for the text in the minibuffer, a completion command completes up to the longest common substring.  If you change `completion-cycle-threshold` to `t`, the completion command instead completes to the first of those completion alternatives; each subsequent invocation of the completion command replaces that with the next completion alternative, in a cyclic manner.  If you give `completion-cycle-threshold` a numeric value n, completion commands switch to this cycling behavior only when there are n or fewer alternatives.



When displaying completions, Emacs will normally pop up a new buffer to display the completions.  The completions will by default be sorted horizontally, using as many columns as will fit in the window-width, but this can be changed by customizing the `completions-format` user option.  If its value is `vertical`, Emacs will sort the completions vertically instead, and if it’s `one-column`, Emacs will use just one column.



The `completions-sort` user option controls the order in which the completions are sorted in the ‘*Completions*’ buffer.  The default is `alphabetical`, which sorts in alphabetical order. The value `nil` disables sorting; the value `historical` sorts alphabetically first, and then rearranges according to the order of the candidates in the minibuffer history.  The value can also be a function, which will be called with the list of completions, and should return the list in the desired order.



When `completions-max-height` is non-`nil`, it limits the size of the completions window.  It is specified in lines and include mode, header line and a bottom divider, if any.  For a more complex control of the Completion window display properties, you can use `display-buffer-alist` (see [Action Alists for Buffer Display](https://www.gnu.org/software/emacs/manual/html_node/elisp/Buffer-Display-Action-Alists.html#Buffer-Display-Action-Alists) in The Emacs Lisp Reference Manual).



The variable `completions-header-format` is a format spec string to control the informative line shown before the completions list of candidates.  If it contains a ‘%s’ construct, that get replaced by the number of completions shown in the completion list buffer.  To suppress the display of the heading line, customize this variable to `nil`.  The string that is the value of this variable can have text properties to change the visual appearance of the heading line; some useful properties `face` or `cursor-intangible` (see [Properties with Special Meanings](https://www.gnu.org/software/emacs/manual/html_node/elisp/Special-Properties.html#Special-Properties) in The Emacs Lisp Reference Manual).



When `completions-highlight-face` names a face, the current completion candidate, the one that will be selected by typing RET or clicking the mouse, will be highlighted using that face.  The default value of this variable is `completions-highlight`; the value is `nil` disables this highlighting.  This feature uses the special text property `cursor-face`.



### 10.5 Minibuffer History



Everything you type in the minibuffer is saved in a *minibuffer history list* so you can easily use it again later.  This includes completion candidates (such as file names, buffer names, command names, etc.) and any other kind of minibuffer input.  You can use the following commands to quickly fetch an earlier or alternative response into the minibuffer:

- M-p

  Move to the previous item in the minibuffer history, an earlier argument (`previous-history-element`).

- M-n

  Move to the next item in the minibuffer history (`next-history-element`).

- UP

- DOWN

  Like M-p and M-n, but move to the previous or next line of a multi-line item before going to the previous history item (`previous-line-or-history-element` and `next-line-or-history-element`) .

- M-r regexp RET

  Move to an earlier item in the minibuffer history that matches regexp (`previous-matching-history-element`).

- M-s regexp RET

  Move to a later item in the minibuffer history that matches regexp (`next-matching-history-element`).



While in the minibuffer, M-p (`previous-history-element`) moves through the minibuffer history list, one item at a time.  Each M-p fetches an earlier item from the history list into the minibuffer, replacing its existing contents.  Typing M-n (`next-history-element`) moves through the minibuffer history list in the opposite direction, fetching later entries into the minibuffer.

If you type M-n in the minibuffer when there are no later entries in the minibuffer history (e.g., if you haven’t previously typed M-p), Emacs tries fetching from a list of default arguments: values that you are likely to enter.  You can think of this as moving through the “future history”.



The “future history” for file names includes several possible alternatives you may find useful, such as the file name or the URL at point in the current buffer.  The defaults put into the “future history” in this case are controlled by the functions mentioned in the value of the option `file-name-at-point-functions`.  By default, its value invokes the `ffap` package (see [Finding Files and URLs at Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/FFAP.html)), which tries to guess the default file or URL from the text around point.  To disable this guessing, customize the option to a `nil` value, then the “future history” of file names will include only the file, if any, visited by the current buffer, and the default directory.



The arrow keys UP and DOWN work like M-p and M-n, but if the current history item is longer than a single line, they allow you to move to the previous or next line of the current history item before going to the previous or next history item.

If you edit the text inserted by the M-p or M-n minibuffer history commands, this does not change its entry in the history list.  However, the edited argument does go at the end of the history list when you submit it.



You can use M-r (`previous-matching-history-element`) to search through older elements in the history list, and M-s (`next-matching-history-element`) to search through newer entries.  Each of these commands asks for a *regular expression* as an argument, and fetches the first matching entry into the minibuffer.  See [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html), for an explanation of regular expressions.  A numeric prefix argument n means to fetch the nth matching entry.  These commands are unusual, in that they use the minibuffer to read the regular expression argument, even though they are invoked from the minibuffer.  An upper-case letter in the regular expression makes the search case-sensitive (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)).

You can also search through the history using an incremental search. See [Searching the Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Isearch-Minibuffer.html).

Emacs keeps separate history lists for several different kinds of arguments.  For example, there is a list for file names, used by all the commands that read file names.  Other history lists include buffer names, command names (used by M-x), and command arguments (used by commands like `query-replace`).



The variable `history-length` specifies the maximum length of a minibuffer history list; adding a new element deletes the oldest element if the list gets too long.  If the value is `t`, there is no maximum length.



The variable `history-delete-duplicates` specifies whether to delete duplicates in history.  If it is non-`nil`, adding a new element deletes from the list all other elements that are equal to it. The default is `nil`.



### 10.6 Repeating Minibuffer Commands



Every command that uses the minibuffer once is recorded on a special history list, the *command history*, together with the values of its arguments, so that you can repeat the entire command.  In particular, every use of M-x is recorded there, since M-x uses the minibuffer to read the command name.



- C-x ESC ESC

  Re-execute a recent minibuffer command from the command history (`repeat-complex-command`).

- M-x list-command-history

  Display the entire command history, showing all the commands C-x ESC ESC can repeat, most recent first.



C-x ESC ESC re-executes a recent command that used the minibuffer.  With no argument, it repeats the last such command. A numeric argument specifies which command to repeat; 1 means the last one, 2 the previous, and so on.

C-x ESC ESC works by turning the previous command into a Lisp expression and then entering a minibuffer initialized with the text for that expression.  Even if you don’t know Lisp, it will probably be obvious which command is displayed for repetition.  If you type just RET, that repeats the command unchanged.  You can also change the command by editing the Lisp expression before you execute it.  The executed command is added to the front of the command history unless it is identical to the most recent item.

Once inside the minibuffer for C-x ESC ESC, you can use the usual minibuffer history commands (see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html)) to move through the history list.  After finding the desired previous command, you can edit its expression as usual and then execute it by typing RET.



Incremental search does not, strictly speaking, use the minibuffer. Therefore, although it behaves like a complex command, it normally does not appear in the history list for C-x ESC ESC. You can make incremental search commands appear in the history by setting `isearch-resume-in-command-history` to a non-`nil` value.  See [Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Incremental-Search.html).



The list of previous minibuffer-using commands is stored as a Lisp list in the variable `command-history`.  Each element is a Lisp expression that describes one command and its arguments.  Lisp programs can re-execute a command by calling `eval` with the `command-history` element.



### 10.7 Entering passwords



Sometimes, you may need to enter a password into Emacs.  For instance, when you tell Emacs to visit a file on another machine via a network protocol such as FTP, you often need to supply a password to gain access to the machine (see [Remote Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Remote-Files.html)).

Entering a password is similar to using a minibuffer.  Emacs displays a prompt in the echo area (such as ‘Password: ’); after you type the required password, press RET to submit it.  To prevent others from seeing your password, every character you type is displayed as an asterisk (‘*’) instead of its usual form.

Most of the features and commands associated with the minibuffer *cannot* be used when entering a password.  There is no history or completion, and you cannot change windows or perform any other action with Emacs until you have submitted the password.

While you are typing the password, you may press DEL to delete backwards, removing the last character entered.

C-u deletes everything you have typed so far.
C-g quits the password prompt (see [Quitting and Aborting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quitting.html)).
C-y inserts the current kill into the password (see [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html)).
TAB toggles the visibility of the password.

You may type either RET or ESC to submit the password.  Any other self-inserting character key inserts the associated character into the password, and all other input is ignored.

There is also an icon in the mode line indicating the password visibility.  Clicking mouse-1 on it toggles the password visibility as well.

### 10.8 Yes or No Prompts

An Emacs command may require you to answer a yes-or-no question during the course of its execution.  Such queries come in two main varieties.



For the first type of yes-or-no query, the prompt ends with ‘(y or n)’.  You answer the query by typing a single key, either ‘y’ or ‘n’, which immediately exits the minibuffer and delivers the response.  For example, if you type C-x C-w (write-file) to save a buffer, and enter the name of an existing file, Emacs issues a prompt like this:

```
File ‘foo.el’ exists; overwrite? (y or n)
```



The second type of yes-or-no query is typically employed if giving the wrong answer would have serious consequences; it thus features a longer prompt ending with ‘(yes or no)’ (or the value of `yes-or-no-prompt` if you’ve customized that).  For example, if you invoke C-x k (`kill-buffer`) on a file-visiting buffer with unsaved changes, Emacs activates the minibuffer with a prompt like this:

```
Buffer foo.el modified; kill anyway? (yes or no)
```

To answer, you must type ‘yes’ or ‘no’ into the minibuffer, followed by RET.

With both types of yes-or-no query the minibuffer behaves as described in the previous sections; you can recenter the selected window with C-l, scroll that window (C-v or PageDown scrolls forward, M-v or PageUp scrolls backward), switch to another window with C-x o, use the history commands M-p and M-n, etc.  Type C-g to dismiss the query, and quit the minibuffer and the querying command (see [Quitting and Aborting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quitting.html)).



## 11 Running Commands by Name

Every Emacs command has a name that you can use to run it.  For convenience, many commands also have key bindings.  You can run those commands by typing the keys, or run them by name.  Most Emacs commands have no key bindings, so the only way to run them is by name. (See [Customizing Key Bindings](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Bindings.html), for how to set up key bindings.)

By convention, a command name consists of one or more words, separated by hyphens; for example, `auto-fill-mode` or `manual-entry`.  Command names mostly use complete English words to make them easier to remember.



To run a command by name, start with M-x, type the command name, then terminate it with RET.  M-x uses the minibuffer to read the command name.  The string ‘M-x’ appears at the beginning of the minibuffer as a *prompt* to remind you to enter a command name to be run.  RET exits the minibuffer and runs the command.  See [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html), for more information on the minibuffer.

You can use completion to enter the command name.  For example, to invoke the command `forward-char`, you can type

```
M-x forward-char RET
```

or

```
M-x forw TAB c RET
```

Note that `forward-char` is the same command that you invoke with the key C-f.  The existence of a key binding does not stop you from running the command by name.



When M-x completes on commands, it ignores the commands that were declared *obsolete* in any previous major version of Emacs; for these, you will have to type their full name.  Commands that were marked obsolete in the current version of Emacs are listed.  (Obsolete commands are those for which newer, better alternatives exist, and which are slated for removal in some future Emacs release.)



In addition, M-x completion can exclude commands that are not relevant to, and generally cannot work with, the current buffer’s major mode (see [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html)) and minor modes (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)).  By default, no commands are excluded, but you can customize the option `read-extended-command-predicate` to exclude those irrelevant commands from completion results.



Conversely, Emacs can exclude all commands except those that are particularly relevant to the current buffer.  The M-S-x (that’s “meta shift x”) command works just like M-x, but instead of listing all (or most) of the commands Emacs knows about, it will only list the commands that have been marked as “belonging” to the current major mode, or any enabled minor modes.

To cancel the M-x and not run a command, type C-g instead of entering the command name.  This takes you back to command level.

To pass a numeric argument to the command you are invoking with M-x, specify the numeric argument before M-x.  The argument value appears in the prompt while the command name is being read, and finally M-x passes the argument to that command.  For example, to pass the numeric argument of 42 to the command `forward-char` you can type C-u 42 M-x forward-char RET.



When the command you run with M-x has a key binding, Emacs mentions this in the echo area after running the command.  For example, if you type M-x forward-word, the message says that you can run the same command by typing M-f.  You can turn off these messages by setting the variable `suggest-key-bindings` to `nil`.  The value of `suggest-key-bindings` can also be a number, in which case Emacs will show the binding for that many seconds before removing it from display.  The default behavior is to display the binding for 2 seconds.

Additionally, when `suggest-key-bindings` is non-`nil`, the completion list of M-x shows equivalent key bindings for all commands that have them.



Commands that don’t have key bindings, can still be invoked after typing less than their full name at the ‘M-x’ prompt.  Emacs mentions such shorthands in the echo area if they are significantly shorter than the full command name, and `extended-command-suggest-shorter` is non-`nil`.  The setting of `suggest-key-bindings` affects these hints as well.

In this manual, when we speak of running a command by name, we often omit the RET that terminates the name.  Thus we might say M-x auto-fill-mode rather than M-x auto-fill-mode RET.  We mention the RET only for emphasis, such as when the command is followed by arguments.



M-x works by running the command `execute-extended-command`, which is responsible for reading the name of another command and invoking it.



## 12 Help



Emacs provides a wide variety of help commands, all accessible through the prefix key C-h (or, equivalently, the function key F1).  These help commands are described in the following sections.  You can also type C-h C-h to view a list of help commands (`help-for-help`).  You can scroll the list with SPC and DEL, then type the help command you want.  To cancel, type C-g.



Many help commands display their information in a special *help buffer*.  In this buffer, you can type SPC and DEL to scroll and type RET to follow hyperlinks.  See [Help Mode Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help-Mode.html).



By default, help commands display the help buffer in a separate window without selecting that window.  The variable `help-window-select` controls this: its default value is `nil`; if it’s customized to the value `t`, the help window is unconditionally selected by help commands, and if its value is `other`, the help window is selected only if there are more than two windows on the selected frame.



Conversely, many commands in the ‘*Help*’ buffer will pop up a new window to display the results.  For instance, clicking on the link to show the source code, or using the i command to display the manual entry, will (by default) pop up a new window.  If `help-window-keep-selected` is changed to non-`nil`, the window displaying the ‘*Help*’ buffer will be reused instead.



If you are looking for a certain feature, but don’t know what it is called or where to look, we recommend three methods.  First, try apropos commands, then try searching the manual index, then look in the FAQ and the package keywords, and finally try listing external packages.

- C-h a topics RET

  This searches for commands whose names match the argument topics.  The argument can be a keyword, a list of keywords separated by whitespace, or a regular expression (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)). See [Apropos](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html).

- C-h d topics RET

  Similar, but searches the *text* of the documentation strings rather than the names of commands and functions.

- C-h r i topic RET

  This searches for topic in the indices of the Emacs Info manual, displaying the first match found.  Press , to see subsequent matches.  You can use a regular expression as topic.

- C-h r s topic RET

  Similar, but searches the *text* of the manual rather than the indices.

- C-h C-f

  This displays the Emacs FAQ, using Info.

- C-h p

  This displays the available Emacs packages based on keywords. See [Keyword Search for Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Package-Keywords.html).

- M-x list-packages

  This displays a list of external packages.  See [Emacs Lisp Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Packages.html).

C-h or F1 mean “help” in various other contexts as well.  For instance, you can type them after a prefix key to view a list of the keys that can follow the prefix key.  (You can also use ? in this context.  A few prefix keys don’t support C-h or ? in this way, because they define other meanings for those inputs, but they all support F1.)



### 12.1 Help Summary

Here is a summary of help commands for accessing the built-in documentation.  Most of these are described in more detail in the following sections.

- C-h a topics RET

  Display a list of commands whose names match topics (`apropos-command`).  See [Apropos](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html).

- C-h b

  Display all active key bindings; minor mode bindings first, then those of the major mode, then global bindings (`describe-bindings`). See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h C-q

  Toggle display of a window showing popular commands and their key bindings.  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h c key

  Show the name of the command that the key sequence key is bound to (`describe-key-briefly`).  Here c stands for “character”.  For more extensive information on key, use C-h k.  See [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html).

- C-h d topics RET

  Display the commands and variables whose documentation matches topics (`apropos-documentation`).  See [Apropos](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html).

- C-h e

  Display the *Messages* buffer (`view-echo-area-messages`).  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h f function RET

  Display documentation on the Lisp function named function (`describe-function`).  Since commands are Lisp functions, this works for commands too, but you can also use `C-h x`.  See [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html).

- C-h h

  Display the HELLO file, which shows examples of various character sets.

- C-h i

  Run Info, the GNU documentation browser (`info`).  The Emacs manual is available in Info.  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h k key

  Display the name and documentation of the command that key runs (`describe-key`).  See [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html).

- C-h l

  Display a description of your last 300 keystrokes (`view-lossage`).  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h m

  Display documentation of the current major mode and minor modes (`describe-mode`).  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h n

  Display news of recent Emacs changes (`view-emacs-news`). See [Help Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help-Files.html).

- C-h o symbol

  Display documentation of the Lisp symbol named symbol (`describe-symbol`).  This will show the documentation of all kinds of symbols: functions, variables, and faces.  See [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html).

- C-h p

  Find packages by topic keyword (`finder-by-keyword`). See [Keyword Search for Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Package-Keywords.html).  This lists packages using a package menu buffer.  See [Emacs Lisp Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Packages.html).

- C-h P package RET

  Display documentation about the specified package (`describe-package`).  See [Keyword Search for Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Package-Keywords.html).

- C-h r

  Display the Emacs manual in Info (`info-emacs-manual`).

- C-h s

  Display the contents of the current *syntax table* (`describe-syntax`).  See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).  The syntax table says which characters are opening delimiters, which are parts of words, and so on.  See [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual, for details.

- C-h t

  Enter the Emacs interactive tutorial (`help-with-tutorial`).

- C-h v var RET

  Display the documentation of the Lisp variable var (`describe-variable`).  See [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html).

- C-h w command RET

  Show which keys run the command named command (`where-is`). See [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html).

- C-h x command RET

  Display documentation on the named command (`describe-command`).  See [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html).

- C-h C coding RET

  Describe the coding system coding (`describe-coding-system`).  See [Coding Systems](https://www.gnu.org/software/emacs/manual/html_node/emacs/Coding-Systems.html).

- C-h C RET

  Describe the coding systems currently in use.

- C-h F command RET

  Enter Info and go to the node that documents the Emacs command command (`Info-goto-emacs-command-node`).  See [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html).

- C-h I method RET

  Describe the input method method (`describe-input-method`). See [Selecting an Input Method](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Input-Method.html).

- C-h K key

  Enter Info and go to the node that documents the key sequence key (`Info-goto-emacs-key-command-node`).  See [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html).

- C-h L language-env RET

  Display information on the character sets, coding systems, and input methods used in language environment language-env (`describe-language-environment`).  See [Language Environments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Language-Environments.html).

- C-h S symbol RET

  Display the Info documentation on symbol symbol according to the programming language you are editing (`info-lookup-symbol`). See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).

- C-h .

  Display the help message for a special text area, if point is in one (`display-local-help`).  (These include, for example, links in *Help* buffers.)  See [Help on Active Text and Tooltips](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help-Echo.html).  If you invoke this command with a prefix argument, C-u C-h ., and point is on a button or a widget, this command will pop a new buffer that describes that button/widget.



### 12.2 Documentation for a Key



The help commands to get information about a key sequence are C-h c (`describe-key-briefly`) and C-h k (`describe-key`).



C-h c key displays in the echo area the name of the command that key is bound to.  For example, C-h c C-f displays ‘forward-char’.



C-h k key is similar but gives more information: it displays a help buffer containing the command’s *documentation string*, which describes exactly what the command does.



C-h K key displays the section of the Emacs manual that describes the command corresponding to key.

C-h c, C-h k and C-h K work for any sort of key sequences, including function keys, menus, and mouse events (except that C-h c ignores mouse movement events).  For instance, after C-h k you can select a menu item from the menu bar, to view the documentation string of the command it runs.



C-h w command RET lists the keys that are bound to command.  It displays the list in the echo area.  If it says the command is not on any key, that means you must use M-x to run it.  C-h w runs the command `where-is`.



Some modes in Emacs use various buttons (see [Buttons](https://www.gnu.org/software/emacs/manual/html_node/elisp/Buttons.html#Buttons) in The Emacs Lisp Reference Manual) and widgets (see [Introduction](https://www.gnu.org/software/emacs/manual/html_node/widget/Introduction.html#Introduction) in Emacs Widgets) that can be clicked to perform some action.  To find out what function is ultimately invoked by these buttons, Emacs provides the `button-describe` and `widget-describe` commands, that should be run with point over the button.



M-x which-key is a global minor mode which helps in discovering keymaps.  It displays keybindings following your currently entered incomplete command (prefix), in a popup.



### 12.3 Help by Command or Variable Name



C-h x command RET (`describe-command`) displays the documentation of the named command, in a window.  For example,

```
C-h x auto-fill-mode RET
```

displays the documentation of `auto-fill-mode`.  This is how you would get the documentation of a command that is not bound to any key (one which you would normally run using M-x).



C-h f function RET (`describe-function`) displays the documentation of Lisp function.  This command is intended for Lisp functions that you use in a Lisp program.  For example, if you have just written the expression `(make-vector len)` and want to check that you are using `make-vector` properly, type C-h f make-vector RET. Additionally, since all commands are Lisp functions, you can also use this command to view the documentation of any command.

If you type C-h f RET, it describes the function called by the innermost Lisp expression in the buffer around point, *provided* that function name is a valid, defined Lisp function. (That name appears as the default while you enter the argument.)  For example, if point is located following the text ‘(make-vector (car x)’, the innermost list containing point is the one that starts with ‘(make-vector’, so C-h f RET describes the function `make-vector`.

C-h f is also useful just to verify that you spelled a function name correctly.  If the minibuffer prompt for C-h f shows the function name from the buffer as the default, it means that name is defined as a Lisp function.  Type C-g to cancel the C-h f command if you don’t really want to view the documentation.

The function’s documentation displayed by `describe-function` includes more than just the documentation string and the signature of the function.  It also shows auxiliary information such as its type, the file where it was defined, whether it has been declared obsolete, and yet further information is often reachable by clicking or typing RET on emphasized parts of the text.



The function type, if known, is expressed with a *function type specifier* (see [Type Specifiers](https://www.gnu.org/software/emacs/manual/html_node/elisp/Type-Specifiers.html#Type-Specifiers) in The Emacs Lisp Reference Manual), it will be specified if the type was manually declared by a Lisp program or inferred by the compiler.  Note that function type inference works only when native compilation is enabled (see [native compilation](https://www.gnu.org/software/emacs/manual/html_node/elisp/native-compilation.html#native-compilation) in The Emacs Lisp Reference Manual).



If you request help for an autoloaded function whose `autoload` form (see [Autoload](https://www.gnu.org/software/emacs/manual/html_node/elisp/Autoload.html#Autoload) in The Emacs Lisp Reference Manual) doesn’t provide a doc string, the *Help* buffer won’t have any doc string to display.  In that case, if `help-enable-symbol-autoload` is non-`nil`, Emacs will try to load the file in which the function is defined to see whether there’s a doc string there.



You can get an overview of functions relevant for a particular topic by using the M-x shortdoc command.  This will prompt you for an area of interest, e.g., `string`, and pop you to a buffer where many of the functions relevant for handling strings are listed.

You can also request that documentation of functions and commands shown in *Help* buffers popped by C-h f includes examples of their use.  To that end, add the following to your initialization file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(add-hook 'help-fns-describe-function-functions
          #'shortdoc-help-fns-examples-function)
```



C-h v (`describe-variable`) is like C-h f but describes Lisp variables instead of Lisp functions.  Its default is the Lisp symbol around or before point, if that is the name of a defined Lisp variable.  See [Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Variables.html).

Help buffers that describe Emacs variables and functions normally have hyperlinks to the corresponding source code, if you have the source files installed (see [Hyperlinking and Web Navigation Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hyperlinking.html)).



To find a command’s documentation in a manual, use C-h F (`Info-goto-emacs-command-node`).  This knows about various manuals, not just the Emacs manual, and finds the right one.



C-h o (`describe-symbol`) is like C-h f and C-h v, but it describes any symbol, be it a function, a variable, or a face.  If the symbol has more than one definition, like it has both definition as a function and as a variable, this command will show the documentation of all of them, one after the other.



C-h 4 s (`help-find-source`) switch to a buffer visiting the source definition of what is being described in the help buffer.



If the `completions-detailed` user option is non-`nil`, some commands provide details about the possible values when displaying completions.  For instance, C-h o TAB will then include the first line of the doc string, and will also say whether each symbol is a function or a variable (and so on).  Which details are included varies depending on the command used.



### 12.4 Apropos



The *apropos* commands answer questions like, “What are the commands for working with files?”  More precisely, you specify your query as an *apropos pattern*, which is either a word, a list of words separated by whitespace, or a regular expression.

Each of the following apropos commands reads an apropos pattern in the minibuffer, searches for items that match the pattern, and displays the results in a different window.

- C-h a[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-C_002dh-a)

  Search for commands (`apropos-command`).  With a prefix argument, search for noninteractive functions too.

- M-x apropos[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos-1)

  Search for functions and variables.  Both interactive functions (commands) and noninteractive functions can be found by this.

- M-x apropos-user-option[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos_002duser_002doption)

  Search for user-customizable variables.  With a prefix argument, search for non-customizable variables too.

- M-x apropos-variable[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos_002dvariable)

  Search for variables.  With a prefix argument, search for customizable variables only.

- M-x apropos-local-variable[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos_002dlocal_002dvariable)

  Search for buffer-local variables.

- M-x apropos-value[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos_002dvalue)

  Search for variables whose values match the specified pattern.  With a prefix argument, search also for functions with definitions matching the pattern, and Lisp symbols with properties matching the pattern.

- M-x apropos-local-value[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-apropos_002dlocal_002dvalue)

  Search for buffer-local variables whose values match the specified pattern.

- C-h d[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Apropos.html#index-C_002dh-d)

  Search for functions and variables whose documentation strings match the specified pattern (`apropos-documentation`).

The simplest kind of apropos pattern is one word.  Anything containing that word matches the pattern.  Thus, to find commands that work on files, type C-h a file RET.  This displays a list of all command names that contain ‘file’, including `copy-file`, `find-file`, and so on.  Each command name comes with a brief description and a list of keys you can currently invoke it with.  In our example, it would say that you can invoke `find-file` by typing C-x C-f.



By default, the window showing the apropos buffer with the results of the query is not selected, but you can cause it to be selected by customizing the variable `help-window-select` to any non-`nil` value.

For more information about a function definition, variable or symbol property listed in an apropos buffer, you can click on it with mouse-1 or mouse-2, or move there and type RET.

When you specify more than one word in the apropos pattern, a name must contain at least two of the words in order to match.  Thus, if you are looking for commands to kill a chunk of text before point, you could try C-h a kill back backward behind before RET.  The real command name `kill-backward` will match that; if there were a command `kill-text-before`, it would also match, since it contains two of the specified words.

For even greater flexibility, you can specify a regular expression (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)).  An apropos pattern is interpreted as a regular expression if it contains any of the regular expression special characters, ‘^$*+?.\[’.

Following the conventions for naming Emacs commands, here are some words that you’ll find useful in apropos patterns.  By using them in C-h a, you will also get a feel for the naming conventions.

> char, line, word, sentence, paragraph, region, page, sexp, list, defun, rect, buffer, frame, window, face, file, dir, register, mode, beginning, end, forward, backward, next, previous, up, down, search, goto, kill, delete, mark, insert, yank, fill, indent, case, change, set, what, list, find, view, describe, default.



If the variable `apropos-do-all` is non-`nil`, most apropos commands behave as if they had been given a prefix argument. There is one exception: `apropos-variable` without a prefix argument will always search for all variables, no matter what the value of `apropos-do-all` is.



By default, all apropos commands except `apropos-documentation` list their results in alphabetical order.  If the variable `apropos-sort-by-scores` is non-`nil`, these commands instead try to guess the relevance of each result, and display the most relevant ones first.  The `apropos-documentation` command lists its results in order of relevance by default; to list them in alphabetical order, change the variable `apropos-documentation-sort-by-scores` to `nil`.



### 12.5 Help Mode Commands



Help buffers have Help mode as their major mode.  Help mode provides the same commands as View mode (see [View Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/View-Mode.html)); for instance, SPC scrolls forward, and DEL or S-SPC scrolls backward.  It also provides a few special commands:

- RET

  Follow a cross-reference at point (`help-follow`).

- TAB

  Move point forward to the next hyperlink (`forward-button`).

- S-TAB

  Move point back to the previous hyperlink (`backward-button`).

- mouse-1

- mouse-2

  Follow a hyperlink that you click on.

- n

- p

  Move forward and back between pages in the Help buffer.

- C-c C-c

  Show all documentation about the symbol at point (`help-follow-symbol`).

- C-c C-f

- r

  Go forward in history of help commands (`help-go-forward`).

- C-c C-b

- l

  Go back in history of help commands (`help-go-back`).

- s

  View the source of the current help topic (if any) (`help-view-source`).

- i

  Look up the current topic in the manual(s) (`help-goto-info`).

- I

  Look up the current topic in the Emacs Lisp manual (`help-goto-lispref-info`).

- c

  Customize the variable or the face (`help-customize`).



When a function name, variable name, or face name (see [Text Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Faces.html)) appears in the documentation in the help buffer, it is normally an underlined *hyperlink*.  To view the associated documentation, move point there and type RET (`help-follow`), or click on the hyperlink with mouse-1 or mouse-2.  Doing so replaces the contents of the help buffer; to retrace your steps, type C-c C-b or l (`help-go-back`).  While retracing your steps, you can go forward by using C-c C-f or r (`help-go-forward`).



To move between hyperlinks in a help buffer, use TAB (`forward-button`) to move forward to the next hyperlink and S-TAB (`backward-button`) to move back to the previous hyperlink.  These commands act cyclically; for instance, typing TAB at the last hyperlink moves back to the first hyperlink.



By default, many links in the help buffer are displayed surrounded by quote characters.  If the `help-clean-buttons` user option is non-`nil`, these quote characters are removed from the buffer.



Help buffers produced by some Help commands (like C-h b, which shows a long list of key bindings) are divided into pages by the ‘^L’ character.  In such buffers, the n (`help-goto-next-page`) command will take you to the next start of page, and the p (`help-goto-previous-page`) command will take you to the previous start of page.  This way you can quickly navigate between the different kinds of documentation in a help buffer.



A help buffer can also contain hyperlinks to Info manuals, source code definitions, and URLs (web pages).  The first two are opened in Emacs, and the third using a web browser via the `browse-url` command (see [Following URLs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Browse_002dURL.html)).

To view all documentation about any symbol in the text, move point to the symbol and type C-c C-c (`help-follow-symbol`). This shows the documentation for all the meanings of the symbol—as a variable, as a function, and/or as a face.

### 12.6 Keyword Search for Packages



Most optional features in Emacs are grouped into *packages*. Emacs contains several hundred built-in packages, and more can be installed over the network (see [Emacs Lisp Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Packages.html)).



To make it easier to find packages related to a topic, most packages are associated with one or more *keywords* based on what they do. Type C-h p (`finder-by-keyword`) to bring up a list of package keywords, together with a description of what the keywords mean.  To view a list of packages for a given keyword, type RET on that line; this displays the list of packages in a Package Menu buffer (see [The Package Menu Buffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Package-Menu.html)).



C-h P (`describe-package`) prompts for the name of a package (see [Emacs Lisp Packages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Packages.html)), and displays a help buffer describing the attributes of the package and the features that it implements.  The buffer lists the keywords that relate to the package in the form of buttons.  Click on a button with mouse-1 or mouse-2 to see the list of other packages related to that keyword.



### 12.7 Help for International Language Support

For information on a specific language environment (see [Language Environments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Language-Environments.html)), type C-h L (`describe-language-environment`).  This displays a help buffer describing the languages supported by the language environment, and listing the associated character sets, coding systems, and input methods, as well as some sample text for that language environment.

The command C-h h (`view-hello-file`) displays the file etc/HELLO, which demonstrates various character sets by showing how to say “hello” in many languages.

The command C-h I (`describe-input-method`) describes an input method—either a specified input method, or by default the input method currently in use.  See [Input Methods](https://www.gnu.org/software/emacs/manual/html_node/emacs/Input-Methods.html).

The command C-h C (`describe-coding-system`) describes coding systems—either a specified coding system, or the ones currently in use.  See [Coding Systems](https://www.gnu.org/software/emacs/manual/html_node/emacs/Coding-Systems.html).



### 12.8 Other Help Commands



C-h i (`info`) runs the Info program, which browses structured documentation files.  C-h 4 i (`info-other-window`) does the same, but shows the Info buffer in another window.  The entire Emacs manual is available within Info, along with many other manuals for the GNU system.  Type h after entering Info to run a tutorial on using Info.



With a numeric argument n, C-h i selects the Info buffer ‘*info*<n>’.  This is useful if you want to browse multiple Info manuals simultaneously.  If you specify just C-u as the prefix argument, C-h i prompts for the name of a documentation file, so you can browse a file which doesn’t have an entry in the top-level Info menu.

The help commands C-h F function RET and C-h K key, described above, enter Info and go straight to the documentation of function or key.



When editing a program, if you have an Info version of the manual for the programming language, you can use C-h S (`info-lookup-symbol`) to find an entry for a symbol (keyword, function or variable) in the proper manual.  The details of how this command works depend on the major mode.



If something surprising happens, and you are not sure what you typed, use C-h l (`view-lossage`).  C-h l displays your last input keystrokes and the commands they invoked.  By default, Emacs stores the last 300 keystrokes; if you wish, you can change this number with the command `lossage-size`. If you see commands that you are not familiar with, you can use C-h k or C-h f to find out what they do.



To review recent echo area messages, use C-h e (`view-echo-area-messages`).  This displays the buffer *Messages*, where those messages are kept.



Each Emacs major mode typically redefines a few keys and makes other changes in how editing works.  C-h m (`describe-mode`) displays documentation on the current major mode, which normally describes the commands and features that are changed in this mode, and also its key bindings.



C-h b (`describe-bindings`) and C-h s (`describe-syntax`) show other information about the current environment within Emacs.  C-h b displays a list of all the key bindings now in effect: first the local bindings of the current minor modes, then the local bindings defined by the current major mode, and finally the global bindings (see [Customizing Key Bindings](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Bindings.html)).  C-h s displays the contents of the syntax table, with explanations of each character’s syntax (see [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual).



C-h C-q (`help-quick-toggle`) toggles on and off the display of a buffer showing the most popular Emacs commands and their respective key bindings (a.k.a. “cheat sheet”).  The contents of that buffer are created by the command `help-quick`.  Each key binding shown in this buffer is a button: click on it with mouse-1 or mouse-2 to show the documentation of the command bound to that key sequence.



You can get a list of subcommands for a particular prefix key by typing C-h, ?, or F1 (`describe-prefix-bindings`) after the prefix key.  (There are a few prefix keys for which not all of these keys work—those that provide their own bindings for that key.  One of these prefix keys is ESC, because ESC C-h and ESC ? are actually C-M-h (`mark-defun`) and M-? (`xref-find-references`), respectively.  However, ESC F1 works fine.)



Finally, M-x describe-keymap prompts for the name of a keymap, with completion, and displays a listing of all key bindings in that keymap.



### 12.9 Help Files

Apart from the built-in documentation and manuals, Emacs contains several other files describing topics like copying conditions, release notes, instructions for debugging and reporting bugs, and so forth. You can use the following commands to view these files.  Apart from C-h g, they all have the form C-h C-char.



- C-h C-c

  Display the rules under which you can copy and redistribute Emacs (`describe-copying`).

- C-h C-d

  Display help for debugging Emacs (`view-emacs-debugging`).

- C-h C-e

  Display information about where to get external packages (`view-external-packages`).

- C-h C-f

  Display the Emacs frequently-answered-questions list (`view-emacs-FAQ`).

- C-h g

  Visit the [page](https://www.gnu.org) with information about the GNU Project (`describe-gnu-project`).

- C-h C-m

  Display information about ordering printed copies of Emacs manuals (`view-order-manuals`).

- C-h C-n

  Display the news, which lists the new features in this version of Emacs (`view-emacs-news`).

- C-h C-o

  Display how to order or download the latest version of Emacs and other GNU software (`describe-distribution`).

- C-h C-p

  Display the list of known Emacs problems, sometimes with suggested workarounds (`view-emacs-problems`).

- C-h C-t

  Display the Emacs to-do list (`view-emacs-todo`).

- C-h C-w

  Display the full details on the complete absence of warranty for GNU Emacs (`describe-no-warranty`).

### 12.10 Help on Active Text and Tooltips



In Emacs, stretches of *active text* (text that does something special in response to mouse clicks or RET) often have associated help text.  This includes hyperlinks in Emacs buffers, as well as parts of the mode line.  On graphical displays, as well as some text terminals which support mouse tracking, moving the mouse over the active text displays the help text as a *tooltip*. See [Tooltips](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tooltips.html).



On terminals that don’t support mouse-tracking, you can display the help text for active buffer text at point by typing C-h . (`display-local-help`).  This shows the help text in the echo area.  To display help text automatically whenever it is available at point, set the variable `help-at-pt-display-when-idle` to `t`.



## 13 The Mark and the Region



Emacs, like many other applications, lets you select some arbitrary part of the buffer text and invoke commands that operate on such *selected text*.  In Emacs, we call the selected text *the region*; its handling is very similar to that of selected text in other programs, but there are also important differences.



The region is the portion of the buffer between *the mark* and the current *point*.  You define a region by setting the mark somewhere (with, for instance, the C-SPC command), and then moving point to where you want the region to end.  (Or you can use the mouse to define a region.)

The region always extends between point and the mark, no matter which of them comes earlier in the text; each time you move point, the region changes.

Setting the mark at a position in the text *activates* it.  When the mark is active, we say also that the region is active; Emacs indicates its extent by highlighting the text within it, using the `region` face (see [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html)).



After certain non-motion commands, including any command that changes the text in the buffer, Emacs automatically *deactivates* the mark; this turns off the highlighting.  You can also explicitly deactivate the mark at any time, by typing C-g (see [Quitting and Aborting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quitting.html)).

Many commands limit the text on which they operate to the active region.  For instance, the M-% command (which replaces matching text) normally works on the entire accessible portion of the buffer, but if you have an active region, it’ll work only on that region instead.

The mark is useful even if it is not active.  For example, you can move to previous mark locations using the mark ring.  See [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html).  Additionally, some commands will have an effect even on an inactive region (for example *upcase-region*).  You can also reactivate the region with commands like C-x C-x.

The above behavior, which is the default in interactive sessions, is known as Transient Mark mode.  Disabling Transient Mark mode switches Emacs to an alternative behavior, in which the region is usually not highlighted.  See [Disabling Transient Mark Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html).



Setting the mark in one buffer has no effect on the marks in other buffers.  When you return to a buffer with an active mark, the mark is at the same place as before.  When multiple windows show the same buffer, they can have different values of point, and thus different regions, but they all share one common mark position.  See [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html). Ordinarily, only the selected window highlights its region; however, if the variable `highlight-nonselected-windows` is non-`nil`, each window highlights its own region.

There is another kind of region: the rectangular region. See [Rectangles](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rectangles.html).



### 13.1 Setting the Mark

Here are some commands for setting the mark:

- C-SPC

  Set the mark at point, and activate it (`set-mark-command`).

- C-@

  The same.

- C-x C-x

  Set the mark at point, and activate it; then move point where the mark used to be (`exchange-point-and-mark`).

- Drag-mouse-1

  Set point and the mark around the text you drag across.

- mouse-3

  Set the mark at point, then move point to where you click (`mouse-save-then-kill`).

- Shifted cursor motion keys

  Set the mark at point if the mark is inactive, then move point. See [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html).



The most common way to set the mark is with C-SPC (`set-mark-command`).  This sets the mark where point is, and activates it.  You can then move point away, leaving the mark behind.  If the mark is already set where point is, this command doesn’t set another mark, but only activates the existing mark.

> There is no C-SPC character in ASCII; usually, typing C-SPC on a text terminal gives the character C-@.  This key is also bound to `set-mark-command`, so unless you are unlucky enough to have a text terminal that behaves differently, you might as well think of C-@ as C-SPC.

For example, suppose you wish to convert part of the buffer to upper case.  To accomplish this, go to one end of the desired text, type C-SPC, and move point until the desired portion of text is highlighted.  Now type C-x C-u (`upcase-region`).  This converts the text in the region to upper case, and then deactivates the mark.

Whenever the mark is active, you can deactivate it by typing C-g (see [Quitting and Aborting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quitting.html)).  Most commands that operate on the region also automatically deactivate the mark, like C-x C-u in the above example.

Instead of setting the mark in order to operate on a region, you can also use it to remember a position in the buffer (by typing C-SPC C-SPC), and later jump back there (by typing C-u C-SPC).  See [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html), for details.



The command C-x C-x (`exchange-point-and-mark`) exchanges the positions of point and the mark.  C-x C-x is useful when you are satisfied with the position of point but want to move the other end of the region (where the mark is).  Using C-x C-x a second time, if necessary, puts the mark at the new position with point back at its original position.  Normally, if the mark is inactive, this command first reactivates the mark wherever it was last set, to ensure that the region is left highlighted.  However, if you call it with a prefix argument, it leaves the mark inactive and the region unhighlighted; you can use this to jump to the mark in a manner similar to C-u C-SPC.

You can also set the mark with the mouse.  If you press the left mouse button (down-mouse-1) and drag the mouse across a range of text, this sets the mark where you first pressed the mouse button and puts point where you release it.  Alternatively, clicking the right mouse button (mouse-3) sets the mark at point and then moves point to where you clicked.  See [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html), for a more detailed description of these mouse commands.

Finally, you can set the mark by holding down the shift key while typing certain cursor motion commands (such as S-RIGHT, S-C-f, S-C-n, etc.).  This is called *shift-selection*. It sets the mark at point before moving point, but only if there is no active mark set via a previous shift-selection or mouse commands.  The mark set by mouse commands and by shift-selection behaves slightly differently from the usual mark: any subsequent unshifted cursor motion command deactivates it automatically.  For details, see [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html).

Many commands that insert text, such as C-y (`yank`), set the mark at the other end of the inserted text, without activating it. This lets you easily return to that position (see [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html)).  You can tell that a command does this when it shows ‘Mark set’ in the echo area.



Under X, every time the active region changes, Emacs saves the text in the region to the *primary selection*.  This lets you insert that text into other X applications with mouse-2 clicks. See [Cut and Paste with Other Window Applications](https://www.gnu.org/software/emacs/manual/html_node/emacs/Primary-Selection.html).



### 13.2 Commands to Mark Textual Objects



Here are commands for placing point and the mark around a textual object such as a word, list, paragraph or page:

- M-@

  Set mark at the end of the next word (`mark-word`).  This does not move point.

- C-M-@

  Set mark after end of following balanced expression (`mark-sexp`).  This does not move point.

- M-h

  Move point to the beginning of the current paragraph, and set mark at the end (`mark-paragraph`).

- C-M-h

  Move point to the beginning of the current defun, and set mark at the end (`mark-defun`).

- C-x C-p

  Move point to the beginning of the current page, and set mark at the end (`mark-page`).

- C-x h

  Move point to the beginning of the buffer, and set mark at the end (`mark-whole-buffer`).



M-@ (`mark-word`) sets the mark at the end of the next word (see [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html), for information about words).  Repeated invocations of this command extend the region by advancing the mark one word at a time.  As an exception, if the mark is active and located before point, M-@ moves the mark backwards from its current position one word at a time.

This command also accepts a numeric argument n, which tells it to advance the mark by n words.  A negative argument −n moves the mark back by n words.



Similarly, C-M-@ (`mark-sexp`) puts the mark at the end of the next balanced expression (see [Expressions with Balanced Parentheses](https://www.gnu.org/software/emacs/manual/html_node/emacs/Expressions.html)).  Repeated invocations extend the region to subsequent expressions, while positive or negative numeric arguments move the mark forward or backward by the specified number of expressions.

The other commands in the above list set both point and mark, so as to delimit an object in the buffer.  M-h (`mark-paragraph`) marks paragraphs (see [Paragraphs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Paragraphs.html)), C-M-h (`mark-defun`) marks top-level definitions (see [Moving by Defuns](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-by-Defuns.html)), and C-x C-p (`mark-page`) marks pages (see [Pages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Pages.html)).  Repeated invocations again play the same role, extending the region to consecutive objects; similarly, numeric arguments specify how many objects to move the mark by.



C-x h (`mark-whole-buffer`) sets up the entire buffer as the region, by putting point at the beginning and the mark at the end.



### 13.3 Operating on the Region



Once you have a region, here are some of the ways you can operate on it:

- Kill it with C-w (see [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html)).
- Copy it to the kill ring with M-w (see [Yanking](https://www.gnu.org/software/emacs/manual/html_node/emacs/Yanking.html)).
- Convert case with C-x C-l or C-x C-u (see [Case Conversion Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Case.html)).
- Undo changes within it using C-u C-/ (see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html)).
- Replace text within it using M-% (see [Query Replace](https://www.gnu.org/software/emacs/manual/html_node/emacs/Query-Replace.html)).
- Indent it with C-x TAB or C-M-\ (see [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html)).
- Fill it as text with M-x fill-region (see [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html)).
- Check the spelling of words within it with M-$ (see [Checking and Correcting Spelling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Spelling.html)).
- Evaluate it as Lisp code with M-x eval-region (see [Evaluating Emacs Lisp Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lisp-Eval.html)).
- Save it in a register with C-x r s (see [Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Registers.html)).
- Save it in a buffer or a file (see [Accumulating Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Accumulating-Text.html)).

Some commands have a default behavior when the mark is inactive, but operate on the region if the mark is active.  For example, M-$ (`ispell-word`) normally checks the spelling of the word at point, but it checks the text in the region if the mark is active (see [Checking and Correcting Spelling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Spelling.html)).  Normally, such commands use their default behavior if the region is empty (i.e., if mark and point are at the same position).  If you want them to operate on the empty region, change the variable `use-empty-active-region` to `t`.



As described in [Erasing Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Erasing.html), the DEL (`backward-delete-char`) and Delete (`delete-forward-char`) commands also act this way.  If the mark is active, they delete the text in the region.  (As an exception, if you supply a numeric argument n, where n is not one, these commands delete n characters regardless of whether the mark is active).  If you change the variable `delete-active-region` to `nil`, then these commands don’t act differently when the mark is active.  If you change the value to `kill`, these commands *kill* the region instead of deleting it (see [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html)).



Other commands always operate on the region, and have no default behavior.  Such commands usually have the word `region` in their names, like C-w (`kill-region`) and C-x C-u (`upcase-region`).  If the mark is inactive, they operate on the *inactive region*—that is, on the text between point and the position at which the mark was last set (see [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html)).  To disable this behavior, change the variable `mark-even-if-inactive` to `nil`.  Then these commands will instead signal an error if the mark is inactive.



By default, text insertion occurs normally even if the mark is active—for example, typing a inserts the character ‘a’, then deactivates the mark.  Delete Selection mode, a minor mode, modifies this behavior: if you enable that mode, then inserting text while the mark is active causes the text in the region to be deleted first.  However, you can tune this behavior by customizing the `delete-selection-temporary-region` option.  Its default value is `nil`, but you can set it to `t`, in which case only temporarily-active regions will be replaced: those which are set by dragging the mouse (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)) or by shift-selection (see [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html)), as well as by C-u C-x C-x when Transient Mark Mode is disabled.  You can further tune the behavior by setting `delete-selection-temporary-region` to `selection`: then temporary regions by C-u C-x C-x won’t be replaced, only the ones activated by dragging the mouse or shift-selection.  To toggle Delete Selection mode on or off, type M-x delete-selection-mode.



### 13.4 The Mark Ring



Each buffer remembers previous locations of the mark, in the *mark ring*.  Commands that set the mark also push the old mark onto this ring.  One of the uses of the mark ring is to remember spots that you may want to go back to.

- C-SPC C-SPC

  Set the mark, pushing it onto the mark ring, without activating it.

- C-u C-SPC

  Move point to where the mark was, and restore the mark from the ring of former marks.



The command C-SPC C-SPC is handy when you want to use the mark to remember a position to which you may wish to return. It pushes the current point onto the mark ring, without activating the mark (which would cause Emacs to highlight the region).  This is actually two consecutive invocations of C-SPC (`set-mark-command`); the first C-SPC sets the mark, and the second C-SPC deactivates it.  (When Transient Mark mode is off, C-SPC C-SPC instead activates Transient Mark mode temporarily; see [Disabling Transient Mark Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html).)



To return to a marked position, use `set-mark-command` with a prefix argument: C-u C-SPC.  This moves point to where the mark was, and deactivates the mark if it was active.  Each subsequent C-u C-SPC jumps to a prior position stored in the mark ring.  The positions you move through in this way are not lost; they go to the end of the ring.



If you set `set-mark-command-repeat-pop` to non-`nil`, then immediately after you type C-u C-SPC, you can type C-SPC instead of C-u C-SPC to cycle through the mark ring.  By default, `set-mark-command-repeat-pop` is `nil`.

Each buffer has its own mark ring.  All editing commands use the current buffer’s mark ring.  In particular, C-u C-SPC always stays in the same buffer.



The variable `mark-ring-max` specifies the maximum number of entries to keep in the mark ring.  This defaults to 16 entries.  If that many entries exist and another one is pushed, the earliest one in the list is discarded.  Repeating C-u C-SPC cycles through the positions currently in the ring.

If you want to move back to the same place over and over, the mark ring may not be convenient enough.  If so, you can record the position in a register for later retrieval (see [Saving Positions in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Position-Registers.html)).



### 13.5 The Global Mark Ring



In addition to the ordinary mark ring that belongs to each buffer, Emacs has a single *global mark ring*.  Each time you set a mark, this is recorded in the global mark ring in addition to the current buffer’s own mark ring, if you have switched buffers since the previous mark setting.  Hence, the global mark ring records a sequence of buffers that you have been in, and, for each buffer, a place where you set the mark.  The length of the global mark ring is controlled by `global-mark-ring-max`, and is 16 by default.

Note that a mark is recorded in the global mark ring only when some command sets the mark.  If an existing mark is merely activated, as is the case when you use C-SPC where a mark is already set (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)), that doesn’t push the mark onto the global ring.



The command C-x C-SPC (`pop-global-mark`) jumps to the buffer and position of the latest entry in the global ring.  It also rotates the ring, so that successive uses of C-x C-SPC take you to earlier buffers and mark positions.



### 13.6 Shift Selection



If you hold down the shift key while typing a cursor motion command, this sets the mark before moving point, so that the region extends from the original position of point to its new position.  This feature is referred to as *shift-selection*.  It is similar to the way text is selected in other editors.

The mark set via shift-selection behaves a little differently from what we have described above.  Firstly, in addition to the usual ways of deactivating the mark (such as changing the buffer text or typing C-g), the mark is deactivated by any *unshifted* cursor motion command.  Secondly, any subsequent *shifted* cursor motion command avoids setting the mark anew.  Therefore, a series of shifted cursor motion commands will continuously adjust the region.

Shift-selection only works if the shifted cursor motion key is not already bound to a separate command (see [Customization](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customization.html)).  For example, if you bind S-C-f to another command, typing S-C-f runs that command instead of performing a shift-selected version of C-f (`forward-char`).

A mark set via mouse commands behaves the same as a mark set via shift-selection (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)).  For example, if you specify a region by dragging the mouse, you can continue to extend the region using shifted cursor motion commands.  In either case, any unshifted cursor motion command deactivates the mark.



To turn off shift-selection, set `shift-select-mode` to `nil`.  Doing so does not disable setting the mark via mouse commands.  If you set `shift-select-mode` to the value `permanent`, cursor motion keys that were not shift-translated will not deactivate the mark, so, for example, the region set by prior commands can be extended by shift-selection, and unshifted cursor motion keys will extend the region set by shift-selection.



### 13.7 Disabling Transient Mark Mode



The default behavior of the mark and region, in which setting the mark activates it and highlights the region, is called Transient Mark mode.  This is a minor mode that is enabled by default in interactive sessions.  It can be toggled with M-x transient-mark-mode, or with the ‘Highlight Active Region’ menu item in the ‘Options’ menu.  Turning it off switches Emacs to an alternative mode of operation:

- Setting the mark, with commands like C-SPC or C-x C-x, does not highlight the region.  Therefore, you can’t tell by looking where the mark is located; you have to remember.

  The usual solution to this problem is to set the mark and then use it soon, before you forget where it is.  You can also check where the mark is by using C-x C-x, which exchanges the positions of the point and the mark (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)).

- Some commands, which ordinarily act on the region when the mark is active, no longer do so.  For example, normally M-% (`query-replace`) performs replacements within the region, if the mark is active.  When Transient Mark mode is off, it always operates from point to the end of the buffer.  Commands that act this way are identified in their own documentation.



While Transient Mark mode is off, you can activate it temporarily using C-SPC C-SPC or C-u C-x C-x.

- C-SPC C-SPC[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html#index-C_002dSPC-C_002dSPC_002c-enabling-Transient-Mark-mode-temporarily)

  Set the mark at point (like plain C-SPC) and enable Transient Mark mode just once, until the mark is deactivated.  (This is not really a separate command; you are using the C-SPC command twice.)

- C-u C-x C-x[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html#index-C_002du-C_002dx-C_002dx)

  Exchange point and mark, activate the mark and enable Transient Mark mode temporarily, until the mark is next deactivated.  (This is the C-x C-x command, `exchange-point-and-mark`, with a prefix argument.)

These commands set or activate the mark, and enable Transient Mark mode only until the mark is deactivated.  One reason you may want to use them is that some commands operate on the entire buffer instead of the region when Transient Mark mode is off.  Enabling Transient Mark mode momentarily gives you a way to use these commands on the region.

When you specify a region with the mouse (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)), or with shift-selection (see [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html)), this likewise activates Transient Mark mode temporarily and highlights the region.



## 14 Killing and Moving Text

In Emacs, *killing* means erasing text and copying it into the *kill ring*.  *Yanking* means bringing text from the kill ring back into the buffer.  (Some applications use the terms “cutting” and “pasting” for similar operations.)  The kill ring is so-named because it can be visualized as a set of blocks of text arranged in a ring, which you can access in cyclic order.  See [The Kill Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kill-Ring.html).

Killing and yanking are the most common way to move or copy text within Emacs.  It is very versatile, because there are commands for killing many different types of syntactic units.



### 14.1 Deletion and Killing



Most commands which erase text from the buffer save it in the kill ring (see [The Kill Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kill-Ring.html)).  These are known as *kill* commands, and their names normally contain the word ‘kill’ (e.g., `kill-line`).  The kill ring stores several recent kills, not just the last one, so killing is a very safe operation: you don’t have to worry much about losing text that you previously killed.  The kill ring is shared by all buffers, so text that is killed in one buffer can be yanked into another buffer.

When you use C-/ (`undo`) to undo a kill command (see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html)), that brings the killed text back into the buffer, but does not remove it from the kill ring.

On graphical displays, killing text also copies it to the system clipboard.  See [“Cut and Paste” Operations on Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cut-and-Paste.html).

Commands that erase text but do not save it in the kill ring are known as *delete* commands; their names usually contain the word ‘delete’.  These include C-d (`delete-char`) and DEL (`delete-backward-char`), which delete only one character at a time, and those commands that delete only spaces or newlines.  Commands that can erase significant amounts of nontrivial data generally do a kill operation instead.

You can also use the mouse to kill and yank.  See [“Cut and Paste” Operations on Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cut-and-Paste.html).

#### 14.1.1 Deletion



Deletion means erasing text and not saving it in the kill ring.  For the most part, the Emacs commands that delete text are those that erase just one character or only whitespace.

- DEL

- BACKSPACE

  Delete the previous character, or the text in the region if it is active (`delete-backward-char`).

- Delete

  Delete the next character, or the text in the region if it is active (`delete-forward-char`).

- C-d

  Delete the next character (`delete-char`).

- M-\

  Delete spaces and tabs around point (`delete-horizontal-space`).

- M-x just-one-space

  Delete spaces and tabs around point, leaving one space.

- M-SPC

  Delete spaces and tabs around point in flexible ways (`cycle-spacing`).

- C-x C-o

  Delete blank lines around the current line (`delete-blank-lines`).

- M-^

  Join two lines by deleting the intervening newline, along with any indentation following it (`delete-indentation`).

We have already described the basic deletion commands DEL (`delete-backward-char`), delete (`delete-forward-char`), and C-d (`delete-char`). See [Erasing Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Erasing.html).  With a numeric argument, they delete the specified number of characters.  If the numeric argument is omitted or one, DEL and delete delete all the text in the region if it is active (see [Operating on the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Using-Region.html)).



The other delete commands are those that delete only whitespace characters: spaces, tabs and newlines.  M-\ (`delete-horizontal-space`) deletes all the spaces and tab characters before and after point.  With a prefix argument, this only deletes spaces and tab characters before point.



M-x just-one-space deletes tabs and spaces around point, but leaves a single space before point, regardless of the number of spaces that existed previously (even if there were none before).  With a numeric argument n, it leaves n spaces before point if n is positive; if n is negative, it deletes newlines in addition to spaces and tabs, leaving −n spaces before point.



The command `cycle-spacing` (M-SPC) acts like a more flexible version of `just-one-space`.  It performs different space cleanup actions defined by `cycle-spacing-actions`, in a cyclic manner, if you call it repeatedly in succession.  By default, the first invocation does the same as `just-one-space`, the second deletes all whitespace characters around point like `delete-horizontal-space`, and the third restores the original whitespace characters; then it cycles.  If invoked with a prefix argument, each action is given that value of the argument.  The user option `cycle-spacing-actions` can include other members; see the doc string of that option for the details.

C-x C-o (`delete-blank-lines`) deletes all blank lines after the current line.  If the current line is blank, it deletes all blank lines preceding the current line as well (leaving one blank line, the current line).  On a solitary blank line, it deletes that line.

M-^ (`delete-indentation`) joins the current line and the previous line, by deleting a newline and all surrounding spaces, usually leaving a single space.  See [M-^](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html).



The command `delete-duplicate-lines` searches the region for identical lines, and removes all but one copy of each.  Normally it keeps the first instance of each repeated line, but with a C-u prefix argument it keeps the last.  With a C-u C-u prefix argument, it only searches for adjacent identical lines.  This is a more efficient mode of operation, useful when the lines have already been sorted.  With a C-u C-u C-u prefix argument, it retains repeated blank lines.

#### 14.1.2 Killing by Lines

- C-k

  Kill rest of line or one or more lines (`kill-line`).

- C-S-backspace

  Kill an entire line at once (`kill-whole-line`)



The simplest kill command is C-k (`kill-line`).  If used at the end of a line, it kills the line-ending newline character, merging the next line into the current one (thus, a blank line is entirely removed).  Otherwise, C-k kills all the text from point up to the end of the line; if point was originally at the beginning of the line, this leaves the line blank.

Spaces and tabs at the end of the line are ignored when deciding which case applies.  As long as point is after the last non-whitespace character in the line, you can be sure that C-k will kill the newline.  To kill an entire non-blank line, go to the beginning and type C-k twice.

In this context, “line” means a logical text line, not a screen line (see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html)).

When C-k is given a positive argument n, it kills n lines and the newlines that follow them (text on the current line before point is not killed).  With a negative argument −n, it kills n lines preceding the current line, together with the text on the current line before point.  C-k with an argument of zero kills the text before point on the current line.



If the variable `kill-whole-line` is non-`nil`, C-k at the very beginning of a line kills the entire line including the following newline.  This variable is normally `nil`.



C-S-backspace (`kill-whole-line`) kills a whole line including its newline, regardless of the position of point within the line.  Note that many text terminals will prevent you from typing the key sequence C-S-backspace.



#### 14.1.3 Other Kill Commands

- C-w

  Kill the region (`kill-region`).

- M-w

  Copy the region into the kill ring (`kill-ring-save`).

- M-d

  Kill the next word (`kill-word`).  See [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html).

- M-DEL

  Kill one word backwards (`backward-kill-word`).

- C-x DEL

  Kill back to beginning of sentence (`backward-kill-sentence`). See [Sentences](https://www.gnu.org/software/emacs/manual/html_node/emacs/Sentences.html).

- M-k

  Kill to the end of the sentence (`kill-sentence`).

- C-M-k

  Kill the following balanced expression (`kill-sexp`).  See [Expressions with Balanced Parentheses](https://www.gnu.org/software/emacs/manual/html_node/emacs/Expressions.html).

- M-z char

  Kill through the next occurrence of char (`zap-to-char`).

- M-x zap-up-to-char char

  Kill up to, but not including, the next occurrence of char.



One of the commonly-used kill commands is C-w (`kill-region`), which kills the text in the region (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)).  Similarly, M-w (`kill-ring-save`) copies the text in the region into the kill ring without removing it from the buffer.  If the mark is inactive when you type C-w or M-w, the command acts on the text between point and where you last set the mark (see [Operating on the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Using-Region.html)).

Emacs also provides commands to kill specific syntactic units: words, with M-DEL and M-d (see [Words](https://www.gnu.org/software/emacs/manual/html_node/emacs/Words.html)); balanced expressions, with C-M-k (see [Expressions with Balanced Parentheses](https://www.gnu.org/software/emacs/manual/html_node/emacs/Expressions.html)); and sentences, with C-x DEL and M-k (see [Sentences](https://www.gnu.org/software/emacs/manual/html_node/emacs/Sentences.html)).



The command M-z (`zap-to-char`) combines killing with searching: it reads a character and kills from point up to (and including) the next occurrence of that character in the buffer.  A numeric argument acts as a repeat count; a negative argument means to search backward and kill text before point.  A history of previously used characters is maintained and can be accessed via the M-p/M-n keystrokes.  This is mainly useful if the character to be used has to be entered via a complicated input method. A similar command `zap-up-to-char` kills from point up to, but not including the next occurrence of a character, with numeric argument acting as a repeat count.



#### 14.1.4 Options for Killing



Some specialized buffers contain *read-only text*, which cannot be modified and therefore cannot be killed.  The kill commands work specially in a read-only buffer: they move over text and copy it to the kill ring, without actually deleting it from the buffer. Normally, they also beep and display an error message when this happens.  But if you set the variable `kill-read-only-ok` to a non-`nil` value, they just print a message in the echo area to explain why the text has not been erased.



Before saving the kill to the kill ring, you can transform the string using `kill-transform-function`.  It’s called with the string to be killed, and it should return the string you want to be saved.  It can also return `nil`, in which case the string won’t be saved to the kill ring.  For instance, if you never want to save a pure white space string to the kill ring, you can say:

```
(setq kill-transform-function
      (lambda (string)
        (and (not (string-blank-p string))
             string)))
```



If you change the variable `kill-do-not-save-duplicates` to a non-`nil` value, identical subsequent kills yield a single kill-ring entry, without duplication.



If you enable the minor mode `kill-ring-deindent-mode`, text saved to the kill-ring will have its indentation decreased by the amount of indentation of the first saved line.  That is, if the first line of the saved text was indented n columns, this mode will remove that number of columns from the indentation of each saved line.



### 14.2 Yanking



*Yanking* means reinserting text previously killed.  The usual way to move or copy text is to kill it and then yank it elsewhere.

- C-y

  Yank the last kill into the buffer, at point (`yank`).

- M-y

  Either replace the text just yanked with an earlier batch of killed text (`yank-pop`), or allow selecting from the list of previously-killed batches of text.  See [Yanking Earlier Kills](https://www.gnu.org/software/emacs/manual/html_node/emacs/Earlier-Kills.html).

- C-M-w

  Cause the following command, if it is a kill command, to append to the previous kill (`append-next-kill`).  See [Appending Kills](https://www.gnu.org/software/emacs/manual/html_node/emacs/Appending-Kills.html).



The basic yanking command is C-y (`yank`).  It inserts the most recent kill, leaving the cursor at the end of the inserted text.  It also sets the mark at the beginning of the inserted text, without activating the mark; this lets you jump easily to that position, if you wish, with C-u C-SPC (see [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html)).

With a plain prefix argument (C-u C-y), the command instead leaves the cursor in front of the inserted text, and sets the mark at the end.  Using any other prefix argument specifies an earlier kill; e.g., C-u 4 C-y reinserts the fourth most recent kill. See [Yanking Earlier Kills](https://www.gnu.org/software/emacs/manual/html_node/emacs/Earlier-Kills.html).

On graphical displays and on capable text-mode displays, C-y first checks if another application has placed any text in the system clipboard more recently than the last Emacs kill.  If so, it inserts the clipboard’s text instead.  Thus, Emacs effectively treats “cut” or “copy” clipboard operations performed in other applications like Emacs kills, except that they are not recorded in the kill ring. See [“Cut and Paste” Operations on Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cut-and-Paste.html), for details.



#### 14.2.1 The Kill Ring

The *kill ring* is a list of blocks of text that were previously killed.  There is only one kill ring, shared by all buffers, so you can kill text in one buffer and yank it in another buffer.  This is the usual way to move text from one buffer to another.  (There are several other methods: for instance, you could store the text in a register; see [Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Registers.html).  See [Accumulating Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Accumulating-Text.html), for some other ways to move text around.)



The maximum number of entries in the kill ring is controlled by the variable `kill-ring-max`.  The default is 120.  If you make a new kill when this limit has been reached, Emacs makes room by deleting the oldest entry in the kill ring.



The actual contents of the kill ring are stored in a variable named `kill-ring`; you can view the entire contents of the kill ring with C-h v kill-ring.



#### 14.2.2 Yanking Earlier Kills



As explained in [Yanking](https://www.gnu.org/software/emacs/manual/html_node/emacs/Yanking.html), you can use a numeric argument to C-y to yank text that is no longer the most recent kill.  This is useful if you remember which kill ring entry you want.  If you don’t, you can use the M-y (`yank-pop`) command to cycle through the possibilities or to select one of the earlier kills.



If the previous command was a yank command, M-y takes the text that was yanked and replaces it with the text from an earlier kill. So, to recover the text of the next-to-the-last kill, first use C-y to yank the last kill, and then use M-y to replace it with the previous kill.  This works only after a C-y or another M-y.  (If M-y is invoked after some other command, it works differently, see below.)

You can understand this operation mode of M-y in terms of a last-yank pointer which points at an entry in the kill ring.  Each time you kill, the last-yank pointer moves to the newly made entry at the front of the ring.  C-y yanks the entry which the last-yank pointer points to.  M-y after a C-y or another M-y moves the last-yank pointer to the previous entry, and the text in the buffer changes to match.  Enough M-y commands one after another can move the pointer to any entry in the ring, so you can get any entry into the buffer.  Eventually the pointer reaches the end of the ring; the next M-y loops back around to the first entry again.

M-y moves the last-yank pointer around the ring, but it does not change the order of the entries in the ring, which always runs from the most recent kill at the front to the oldest one still remembered.

When used after C-y or M-y, M-y can take a numeric argument, which tells it how many entries to advance the last-yank pointer by.  A negative argument moves the pointer toward the front of the ring; from the front of the ring, it moves around to the last entry and continues forward from there.

Once the text you are looking for is brought into the buffer, you can stop doing M-y commands and the last yanked text will stay there.  It’s just a copy of the kill ring entry, so editing it in the buffer does not change what’s in the ring.  As long as no new killing is done, the last-yank pointer remains at the same place in the kill ring, so repeating C-y will yank another copy of the same previous kill.

When you call C-y with a numeric argument, that also sets the last-yank pointer to the entry that it yanks.

You can also invoke M-y after a command that is not a yank command.  In that case, M-y prompts you in the minibuffer for one of the previous kills.  You can use the minibuffer history commands (see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html)) to navigate or search through the entries in the kill ring until you find the one you want to reinsert.  Or you can use completion commands (see [Completion Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Commands.html)) to complete on an entry from the list of entries in the kill ring or pop up the *Completions* buffer with the candidate entries from which you can choose.  After selecting the kill-ring entry, you can optionally edit it in the minibuffer.  Finally, type RET to exit the minibuffer and insert the text of the selected kill-ring entry.  Like in case of M-y after another yank command, the last-yank pointer is left pointing at the text you just yanked, whether it is one of the previous kills or an entry from the kill-ring that you edited before inserting it.  (In the latter case, the edited entry is added to the front of the kill-ring.)  So here, too, typing C-y will yank another copy of the text just inserted.

When invoked with a plain prefix argument (C-u M-y) after a command that is not a yank command, M-y leaves the cursor in front of the inserted text, and sets the mark at the end, like C-y does.



#### 14.2.3 Appending Kills



Normally, each kill command pushes a new entry onto the kill ring. However, two or more kill commands in a row combine their text into a single entry, so that a single C-y yanks all the text as a unit, just as it was before it was killed.

Thus, if you want to yank text as a unit, you need not kill all of it with one command; you can keep killing line after line, or word after word, until you have killed it all, and you can still get it all back at once.

Commands that kill forward from point add onto the end of the previous killed text.  Commands that kill backward from point add text onto the beginning.  This way, any sequence of mixed forward and backward kill commands puts all the killed text into one entry without rearrangement. Numeric arguments do not break the sequence of appending kills.  For example, suppose the buffer contains this text:

```
This is a line ∗of sample text.
```

with point shown by ∗.  If you type M-d M-DEL M-d M-DEL, killing alternately forward and backward, you end up with ‘a line of sample’ as one entry in the kill ring, and ‘This is text.’ in the buffer.  (Note the double space between ‘is’ and ‘text’, which you can clean up with M-SPC or M-q.)

Another way to kill the same text is to move back two words with M-b M-b, then kill all four words forward with C-u M-d. This produces exactly the same results in the buffer and in the kill ring.  M-f M-f C-u M-DEL kills the same text, all going backward; once again, the result is the same.  The text in the kill ring entry always has the same order that it had in the buffer before you killed it.



If a kill command is separated from the last kill command by other commands (not just numeric arguments), it starts a new entry on the kill ring.  But you can force it to combine with the last killed text, by typing C-M-w (`append-next-kill`) right beforehand.  The C-M-w tells its following command, if it is a kill command, to treat the kill as part of the sequence of previous kills.  As usual, the kill is appended to the previous killed text if the command kills forward, and prepended if the command kills backward.  In this way, you can kill several separated pieces of text and accumulate them to be yanked back in one place.

A kill command following M-w (`kill-ring-save`) does not append to the text that M-w copied into the kill ring.



### 14.3 “Cut and Paste” Operations on Graphical Displays



In most graphical desktop environments, you can transfer data (usually text) between different applications using a system facility called the *clipboard*.  On X, two other similar facilities are available: the primary selection and the secondary selection.  When Emacs is run on a graphical display, its kill and yank commands integrate with these facilities, so that you can easily transfer text between Emacs and other graphical applications.

By default, Emacs uses UTF-8 as the coding system for inter-program text transfers.  If you find that the pasted text is not what you expected, you can specify another coding system by typing C-x RET x or C-x RET X.  You can also request a different data type by customizing `x-select-request-type`. See [Coding Systems for Interprocess Communication](https://www.gnu.org/software/emacs/manual/html_node/emacs/Communication-Coding.html).



#### 14.3.1 Using the Clipboard



The *clipboard* is the facility that most graphical applications use for “cutting and pasting”.  When the clipboard exists, the kill and yank commands in Emacs make use of it.

When you kill some text with a command such as C-w (`kill-region`), or copy it to the kill ring with a command such as M-w (`kill-ring-save`), that text is also put in the clipboard.



When an Emacs kill command puts text in the clipboard, the existing clipboard contents are normally lost.  Optionally, Emacs can save the existing clipboard contents to the kill ring, preventing you from losing the old clipboard data.  If `save-interprogram-paste-before-kill` has been set to a number, then the data is copied over if it’s smaller (in characters) than this number.  If this variable is any other non-`nil` value, the data is always copied over—at the risk of high memory consumption if that data turns out to be large.

Yank commands, such as C-y (`yank`), also use the clipboard.  If another application “owns” the clipboard—i.e., if you cut or copied text there more recently than your last kill command in Emacs—then Emacs yanks from the clipboard instead of the kill ring.



Normally, rotating the kill ring with M-y (`yank-pop`) does not alter the clipboard.  However, if you change `yank-pop-change-selection` to `t`, then M-y saves the new yank to the clipboard.



To prevent kill and yank commands from accessing the clipboard, change the variable `select-enable-clipboard` to `nil`.



Programs can put other things than plain text on the clipboard.  For instance, a web browser will usually let you choose “Copy Image” on images, and this image will be put on the clipboard.  On capable platforms, Emacs can yank these objects with the `yank-media` command—but only in modes that have support for it (see [Yanking Media](https://www.gnu.org/software/emacs/manual/html_node/elisp/Yanking-Media.html#Yanking-Media) in The Emacs Lisp Reference Manual).



Many X desktop environments support a feature called the *clipboard manager*.  If you exit Emacs while it is the current “owner” of the clipboard data, and there is a clipboard manager running, Emacs transfers the clipboard data to the clipboard manager so that it is not lost.  In some circumstances, this may cause a delay when exiting Emacs; if you wish to prevent Emacs from transferring data to the clipboard manager, change the variable `x-select-enable-clipboard-manager` to `nil`.

Since strings containing NUL bytes are usually truncated when passed through the clipboard, Emacs replaces such characters with “\0” before transferring them to the system’s clipboard.



Prior to Emacs 24, the kill and yank commands used the primary selection (see [Cut and Paste with Other Window Applications](https://www.gnu.org/software/emacs/manual/html_node/emacs/Primary-Selection.html)), not the clipboard.  If you prefer this behavior, change `select-enable-clipboard` to `nil`, `select-enable-primary` to `t`, and `mouse-drag-copy-region` to `t`.  In this case, you can use the following commands to act explicitly on the clipboard: `clipboard-kill-region` kills the region and saves it to the clipboard; `clipboard-kill-ring-save` copies the region to the kill ring and saves it to the clipboard; and `clipboard-yank` yanks the contents of the clipboard at point.



#### 14.3.2 Cut and Paste with Other Window Applications



Under the X Window System, PGTK and Haiku, there exists a *primary selection* containing the last stretch of text selected in an X application (usually by dragging the mouse).  Typically, this text can be inserted into other X applications by mouse-2 clicks.  The primary selection is separate from the clipboard.  Its contents are more fragile; they are overwritten each time you select text with the mouse, whereas the clipboard is only overwritten by explicit cut or copy commands.

Under X, whenever the region is active (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)), the text in the region is saved in the primary selection.  This applies regardless of whether the region was made by dragging or clicking the mouse (see [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html)), or by keyboard commands (e.g., by typing C-SPC and moving point; see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)).



If you change the variable `select-active-regions` to `only`, Emacs saves only temporarily active regions to the primary selection, i.e., those made with the mouse or with shift selection (see [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html)).  If you change `select-active-regions` to `nil`, Emacs avoids saving active regions to the primary selection entirely.

To insert the primary selection into an Emacs buffer, click mouse-2 (`mouse-yank-primary`) where you want to insert it. See [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html).  You can also use the normal Emacs yank command (C-y) to insert this text if `select-enable-primary` is set (see [Using the Clipboard](https://www.gnu.org/software/emacs/manual/html_node/emacs/Clipboard.html)).



By default, Emacs keeps the region active even after text is selected in another program; this is contrary to typical X behavior. To make Emacs deactivate the region after another program places data in the primary selection, enable the global minor mode `lost-selection-mode`.



MS-Windows provides no primary selection, but Emacs emulates it within a single Emacs session by storing the selected text internally. Therefore, all the features and commands related to the primary selection work on Windows as they do on X, for cutting and pasting within the same session, but not across Emacs sessions or with other applications.



#### 14.3.3 Secondary Selection



In addition to the primary selection, the X Window System provides a second similar facility known as the *secondary selection*. Nowadays, few X applications make use of the secondary selection, but you can access it using the following Emacs commands:

- M-Drag-mouse-1

  Set the secondary selection, with one end at the place where you press down the button, and the other end at the place where you release it (`mouse-set-secondary`).  The selected text is highlighted, using the `secondary-selection` face, as you drag.  The window scrolls automatically if you drag the mouse off the top or bottom of the window, just like `mouse-set-region` (see [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html)). This command does not alter the kill ring.

- M-mouse-1

  Set one endpoint for the *secondary selection* (`mouse-start-secondary`); use M-mouse-3 to set the other end and complete the selection.  This command cancels any existing secondary selection, when it starts a new one.

- M-mouse-3

  Set the secondary selection (`mouse-secondary-save-then-kill`), with one end at the position you click M-mouse-3, and the other at the position specified previously with M-mouse-1.  This also puts the selected text in the kill ring.  A second M-mouse-3 at the same place kills the text selected by the secondary selection just made.

- M-mouse-2

  Insert the secondary selection where you click, placing point at the end of the yanked text (`mouse-yank-secondary`).

Double or triple clicking of M-mouse-1 operates on words and lines, much like mouse-1.

If `mouse-yank-at-point` is non-`nil`, M-mouse-2 yanks at point.  Then it does not matter precisely where you click, or even which of the frame’s windows you click on.  See [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html). This user option also effects interactive search: if it is non-`nil`, yanking with the mouse anywhere in the frame will add the text to the search string.



### 14.4 Accumulating Text



Usually we copy or move text by killing it and yanking it, but there are other convenient methods for copying one block of text in many places, or for copying many scattered blocks of text into one place. Here we describe the commands to accumulate scattered pieces of text into a buffer or into a file.

- M-x append-to-buffer

  Append region to the contents of a specified buffer.

- M-x prepend-to-buffer

  Prepend region to the contents of a specified buffer.

- M-x copy-to-buffer

  Copy region into a specified buffer, deleting that buffer’s old contents.

- M-x insert-buffer

  Insert the contents of a specified buffer into current buffer at point.

- M-x append-to-file

  Append region to the contents of a specified file, at the end.

To accumulate text into a buffer, use M-x append-to-buffer. This reads a buffer name, then inserts a copy of the region into the buffer specified.  If you specify a nonexistent buffer, `append-to-buffer` creates the buffer.  The text is inserted wherever point is in that buffer.  If you have been using the buffer for editing, the copied text goes into the middle of the text of the buffer, starting from wherever point happens to be at that moment.

Point in that buffer is left at the end of the copied text, so successive uses of `append-to-buffer` accumulate the text in the specified buffer in the same order as they were copied.  Strictly speaking, `append-to-buffer` does not always append to the text already in the buffer—it appends only if point in that buffer is at the end.  However, if `append-to-buffer` is the only command you use to alter a buffer, then point is always at the end.

M-x prepend-to-buffer is just like `append-to-buffer` except that point in the other buffer is left before the copied text, so successive uses of this command add text in reverse order.  M-x copy-to-buffer is similar, except that any existing text in the other buffer is deleted, so the buffer is left containing just the text newly copied into it.

The command C-x x i (`insert-buffer`) can be used to retrieve the accumulated text from another buffer.  This prompts for the name of a buffer, and inserts a copy of all the text in that buffer into the current buffer at point, leaving point at the beginning of the inserted text.  It also adds the position of the end of the inserted text to the mark ring, without activating the mark. See [Using Multiple Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Buffers.html), for background information on buffers.

Instead of accumulating text in a buffer, you can append text directly into a file with M-x append-to-file.  This prompts for a filename, and adds the text of the region to the end of the specified file.  The file is changed immediately on disk.

You should use `append-to-file` only with files that are *not* being visited in Emacs.  Using it on a file that you are editing in Emacs would change the file behind Emacs’s back, which can lead to losing some of your editing.

Another way to move text around is to store it in a register. See [Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Registers.html).



### 14.5 Rectangles



*Rectangle* commands operate on rectangular areas of the text: all the characters between a certain pair of columns, in a certain range of lines.  Emacs has commands to kill rectangles, yank killed rectangles, clear them out, fill them with blanks or text, or delete them.  Rectangle commands are useful with text in multicolumn formats, and for changing text into or out of such formats.



To specify a rectangle for a command to work on, set the mark at one corner and point at the opposite corner.  The rectangle thus specified is called the *region-rectangle*.  If point and the mark are in the same column, the region-rectangle is empty.  If they are in the same line, the region-rectangle is one line high.

The region-rectangle is controlled in much the same way as the region is controlled.  But remember that a given combination of point and mark values can be interpreted either as a region or as a rectangle, depending on the command that uses them.

A rectangular region can also be marked using the mouse: click and drag C-M-mouse-1 from one corner of the rectangle to the opposite.

- C-x r k

  Kill the text of the region-rectangle, saving its contents as the last killed rectangle (`kill-rectangle`).

- C-x r M-w

  Save the text of the region-rectangle as the last killed rectangle (`copy-rectangle-as-kill`).

- C-x r d

  Delete the text of the region-rectangle (`delete-rectangle`).

- C-x r y

  Yank the last killed rectangle with its upper left corner at point (`yank-rectangle`).

- C-x r o

  Insert blank space to fill the space of the region-rectangle (`open-rectangle`).  This pushes the previous contents of the region-rectangle to the right.

- C-x r N

  Insert line numbers along the left edge of the region-rectangle (`rectangle-number-lines`).  This pushes the previous contents of the region-rectangle to the right.

- C-x r c

  Clear the region-rectangle by replacing all of its contents with spaces (`clear-rectangle`).

- M-x delete-whitespace-rectangle

  Delete whitespace in each of the lines on the specified rectangle, starting from the left edge column of the rectangle.

- C-x r t string RET

  Replace rectangle contents with string on each line (`string-rectangle`).

- M-x string-insert-rectangle RET string RET

  Insert string on each line of the rectangle.

- C-x SPC

  Toggle Rectangle Mark mode (`rectangle-mark-mode`). When this mode is active, the region-rectangle is highlighted and can be shrunk/grown, and the standard kill and yank commands operate on it.

The rectangle operations fall into two classes: commands to erase or insert rectangles, and commands to make blank rectangles.



There are two ways to erase the text in a rectangle: C-x r d (`delete-rectangle`) to delete the text outright, or C-x r k (`kill-rectangle`) to remove the text and save it as the *last killed rectangle*.  In both cases, erasing the region-rectangle is like erasing the specified text on each line of the rectangle; if there is any following text on the line, it moves backwards to fill the gap.

Killing a rectangle is not killing in the usual sense; the rectangle is not stored in the kill ring, but in a special place that only records the most recent rectangle killed.  This is because yanking a rectangle is so different from yanking linear text that different yank commands have to be used.  Yank-popping is not defined for rectangles.



C-x r M-w (`copy-rectangle-as-kill`) is the equivalent of M-w for rectangles: it records the rectangle as the last killed rectangle, without deleting the text from the buffer.



To yank the last killed rectangle, type C-x r y (`yank-rectangle`).  The rectangle’s first line is inserted at point, the rectangle’s second line is inserted at the same horizontal position one line vertically below, and so on.  The number of lines affected is determined by the height of the saved rectangle.

For example, you can convert two single-column lists into a double-column list by killing one of the single-column lists as a rectangle, and then yanking it beside the other list.

You can also copy rectangles into and out of registers with C-x r r r and C-x r i r.  See [Saving Rectangles in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rectangle-Registers.html).



There are two commands you can use for making blank rectangles: C-x r c (`clear-rectangle`) blanks out existing text in the region-rectangle, and C-x r o (`open-rectangle`) inserts a blank rectangle.



M-x delete-whitespace-rectangle deletes horizontal whitespace starting from a particular column.  This applies to each of the lines in the rectangle, and the column is specified by the left edge of the rectangle.  The right edge of the rectangle does not make any difference to this command.



The command C-x r N (`rectangle-number-lines`) inserts line numbers along the left edge of the region-rectangle.  Normally, the numbering begins from 1 (for the first line of the rectangle). With a prefix argument, the command prompts for a number to begin from, and for a format string with which to print the numbers (see [Formatting Strings](https://www.gnu.org/software/emacs/manual/html_node/elisp/Formatting-Strings.html#Formatting-Strings) in The Emacs Lisp Reference Manual).



The command C-x r t (`string-rectangle`) replaces the contents of a region-rectangle with a string on each line.  The string’s width need not be the same as the width of the rectangle.  If the string’s width is less, the text after the rectangle shifts left; if the string is wider than the rectangle, the text after the rectangle shifts right.



The command M-x string-insert-rectangle is similar to `string-rectangle`, but inserts the string on each line, shifting the original text to the right.



The command C-x SPC (`rectangle-mark-mode`) toggles whether the region-rectangle or the standard region is highlighted (first activating the region if necessary).  When this mode is enabled, commands that resize the region (C-f, C-n etc.) do so in a rectangular fashion, and killing and yanking operate on the rectangle.  See [Killing and Moving Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Killing.html).  The mode persists only as long as the region is active.

The region-rectangle works only when the mark is active.  In particular, when Transient Mark mode is off (see [Disabling Transient Mark Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html)), in addition to typing C-x SPC you will need to activate the mark.

Unlike the standard region, the region-rectangle can have its corners extended past the end of buffer, or inside stretches of white space that point normally cannot enter, like in the middle of a TAB character.



When the region is active (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)) and in rectangle-mark-mode, C-x C-x runs the command `rectangle-exchange-point-and-mark`, which cycles between the four corners of the region-rectangle.  This comes in handy if you want to modify the dimensions of the region-rectangle before invoking an operation on the marked text.



### 14.6 CUA Bindings



The command M-x cua-mode sets up key bindings that are compatible with the Common User Access (CUA) system used in many other applications.

When CUA mode is enabled, the keys C-x, C-c, C-v, and C-z invoke commands that cut (kill), copy, paste (yank), and undo respectively.  The C-x and C-c keys perform cut and copy only if the region is active.  Otherwise, they still act as prefix keys, so that standard Emacs commands like C-x C-c still work.  Note that this means the variable `mark-even-if-inactive` has no effect for C-x and C-c (see [Operating on the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Using-Region.html)).

To enter an Emacs command like C-x C-f while the mark is active, use one of the following methods: either hold Shift together with the prefix key, e.g., S-C-x C-f, or quickly type the prefix key twice, e.g., C-x C-x C-f.

To disable the overriding of standard Emacs binding by CUA mode, while retaining the other features of CUA mode described below, set the variable `cua-enable-cua-keys` to `nil`.

CUA mode by default activates Delete-Selection mode (see [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html)) so that typed text replaces the active region.  To use CUA without this behavior, set the variable `cua-delete-selection` to `nil`.



CUA mode provides enhanced rectangle support with visible rectangle highlighting.  Use C-RET to start a rectangle, extend it using the movement commands, and cut or copy it using C-x or C-c.  RET moves the cursor to the next (clockwise) corner of the rectangle, so you can easily expand it in any direction.  Normal text you type is inserted to the left or right of each line in the rectangle (on the same side as the cursor).

You can use this rectangle support without activating CUA by calling the `cua-rectangle-mark-mode` command.  There’s also the standard command `rectangle-mark-mode`, see [Rectangles](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rectangles.html).

With CUA you can easily copy text and rectangles into and out of registers by providing a one-digit numeric prefix to the kill, copy, and yank commands, e.g., C-1 C-c copies the region into register `1`, and C-2 C-v yanks the contents of register `2`.



CUA mode also has a global mark feature which allows easy moving and copying of text between buffers.  Use C-S-SPC to toggle the global mark on and off.  When the global mark is on, all text that you kill or copy is automatically inserted at the global mark, and text you type is inserted at the global mark rather than at the current position.

For example, to copy words from various buffers into a word list in a given buffer, set the global mark in the target buffer, then navigate to each of the words you want in the list, mark it (e.g., with S-M-f), copy it to the list with C-c or M-w, and insert a newline after the word in the target list by pressing RET.



## 15 Registers



Emacs *registers* are compartments where you can save text, rectangles, positions, and other things for later use.  Once you save text or a rectangle in a register, you can copy it into the buffer once or many times; once you save a position in a register, you can jump back to that position once or many times.

Each register has a name that consists of a single character, which we will denote by r; r can be a letter (such as ‘a’) or a number (such as ‘1’); case matters, so register ‘a’ is not the same as register ‘A’.  You can also set a register in non-alphanumeric characters, for instance ‘C-d’ by using for example C-q ‘C-d’.



A register can store a position, a piece of text, a rectangle, a number, a window or frame configuration, a buffer name, or a file name, but only one thing at any given time.  Whatever you store in a register remains there until you store something else in that register.  To see what register r contains, use M-x view-register:

- M-x view-register RET r

  Display a description of what register r contains.



All of the commands that prompt for a register will by default display a preview window that lists the existing registers (if there are any) and their current values, after a short delay.  This and other aspects of prompting for a register can be customized by setting the value of `register-use-preview`, which can have the following values:

- `traditional`

  With this value, which is the default, Emacs behaves like it did in all the versions before Emacs 29: it shows a preview of existing registers after a delay, and lets you overwrite the values of existing registers by typing a single character, the name of the register.  The preview appears after the delay determined by the customizable variable `register-preview-delay`, which specifies the delay in seconds; setting it to `nil` disables the preview (but you can still explicitly request a preview window by pressing C-h or F1 when Emacs prompts for a register).

- `t`

  This value requests a more flexible preview of existing registers. The preview appears immediately when Emacs prompts for a register (thus `register-preview-delay` has no effect), and the preview window provides navigation: by using C-n and C-p (or the UP and DOWN arrow keys), you can move between the registers in the preview window.  To overwrite the value of an existing registers in this mode, you need to type RET after selecting the register by navigation or typing its name. In addition, the registers shown by the preview are filtered according to the command that popped the preview: for example, the preview shown by `insert-register` will only show registers whose values can be inserted into the buffer, omitting registers which hold window configurations, positions, and other un-insertable values.

- `insist`

  This value is like `t`, but in addition you can press the same key as the name of register one more time to exit the minibuffer, instead of pressing RET.

- `nil`

  This value requests behavior similar to `traditional`, but the preview is shown without delay, and is filtered according to the command.

- `never`

  This value is like `nil`, but it disables the preview.

*Bookmarks* record files and positions in them, so you can return to those positions when you look at the file again.  Bookmarks are similar in spirit to registers, so they are also documented in this chapter.



## 15 Registers



Emacs *registers* are compartments where you can save text, rectangles, positions, and other things for later use.  Once you save text or a rectangle in a register, you can copy it into the buffer once or many times; once you save a position in a register, you can jump back to that position once or many times.

Each register has a name that consists of a single character, which we will denote by r; r can be a letter (such as ‘a’) or a number (such as ‘1’); case matters, so register ‘a’ is not the same as register ‘A’.  You can also set a register in non-alphanumeric characters, for instance ‘C-d’ by using for example C-q ‘C-d’.



A register can store a position, a piece of text, a rectangle, a number, a window or frame configuration, a buffer name, or a file name, but only one thing at any given time.  Whatever you store in a register remains there until you store something else in that register.  To see what register r contains, use M-x view-register:

- M-x view-register RET r

  Display a description of what register r contains.



All of the commands that prompt for a register will by default display a preview window that lists the existing registers (if there are any) and their current values, after a short delay.  This and other aspects of prompting for a register can be customized by setting the value of `register-use-preview`, which can have the following values:

- `traditional`

  With this value, which is the default, Emacs behaves like it did in all the versions before Emacs 29: it shows a preview of existing registers after a delay, and lets you overwrite the values of existing registers by typing a single character, the name of the register.  The preview appears after the delay determined by the customizable variable `register-preview-delay`, which specifies the delay in seconds; setting it to `nil` disables the preview (but you can still explicitly request a preview window by pressing C-h or F1 when Emacs prompts for a register).

- `t`

  This value requests a more flexible preview of existing registers. The preview appears immediately when Emacs prompts for a register (thus `register-preview-delay` has no effect), and the preview window provides navigation: by using C-n and C-p (or the UP and DOWN arrow keys), you can move between the registers in the preview window.  To overwrite the value of an existing registers in this mode, you need to type RET after selecting the register by navigation or typing its name. In addition, the registers shown by the preview are filtered according to the command that popped the preview: for example, the preview shown by `insert-register` will only show registers whose values can be inserted into the buffer, omitting registers which hold window configurations, positions, and other un-insertable values.

- `insist`

  This value is like `t`, but in addition you can press the same key as the name of register one more time to exit the minibuffer, instead of pressing RET.

- `nil`

  This value requests behavior similar to `traditional`, but the preview is shown without delay, and is filtered according to the command.

- `never`

  This value is like `nil`, but it disables the preview.

*Bookmarks* record files and positions in them, so you can return to those positions when you look at the file again.  Bookmarks are similar in spirit to registers, so they are also documented in this chapter.



### 15.2 Saving Text in Registers



When you want to insert a copy of the same piece of text several times, it may be inconvenient to yank it from the kill ring, since each subsequent kill moves that entry further down the ring.  An alternative is to store the text in a register and later retrieve it.

- C-x r s r

  Copy region into register r (`copy-to-register`).

- C-x r i r

  Insert text from register r (`insert-register`).

- M-x append-to-register RET r

  Append region to text in register r. When register r contains text, you can use C-x r + (`increment-register`) to append to that register.  Note that command C-x r + behaves differently if r contains a number.  See [Keeping Numbers in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Number-Registers.html).

- M-x prepend-to-register RET r

  Prepend region to text in register r.



C-x r s r stores a copy of the text of the region into the register named r.  If the mark is inactive, Emacs first reactivates the mark where it was last set.  The mark is deactivated at the end of this command.  See [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html).  C-u C-x r s r, the same command with a prefix argument, copies the text into register r and deletes the text from the buffer as well; you can think of this as moving the region text into the register.



M-x append-to-register RET r appends the copy of the text in the region to the text already stored in the register named r.  If invoked with a prefix argument, it deletes the region after appending it to the register.  The command `prepend-to-register` is similar, except that it *prepends* the region text to the text in the register instead of *appending* it.



When you are collecting text using `append-to-register` and `prepend-to-register`, you may want to separate individual collected pieces using a separator.  In that case, configure a `register-separator` and store the separator text in to that register.  For example, to get double newlines as text separator during the collection process, you can use the following setting.

```
(setq register-separator ?+)
(set-register register-separator "\n\n")
```



C-x r i r inserts in the buffer the text from register r.  Normally it leaves point after the text and sets the mark before, without activating it.  With a prefix argument, it instead puts point before the text and the mark after.



### 15.3 Saving Rectangles in Registers



A register can contain a rectangle instead of linear text. See [Rectangles](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rectangles.html), for basic information on how to specify a rectangle in the buffer.

- C-x r r r

  Copy the region-rectangle into register r (`copy-rectangle-to-register`).  With prefix argument, delete it as well.

- C-x r i r

  Insert the rectangle stored in register r (if it contains a rectangle) (`insert-register`).

The C-x r i r (`insert-register`) command, previously documented in [Saving Text in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Registers.html), inserts a rectangle rather than a text string, if the register contains a rectangle.



### 15.4 Saving Window and Frame Configurations in Registers



You can save the window configuration of the selected frame in a register, or even the configuration of all windows in all frames, and restore the configuration later.  See [Convenience Features for Window Handling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Convenience.html), for information about window configurations.

- C-x r w r

  Save the state of the selected frame’s windows in register r (`window-configuration-to-register`).

- C-x r f r

  Save the state of all frames, including all their windows (a.k.a. *frameset*), in register r (`frameset-to-register`).

Use C-x r j r to restore a window or frame configuration. This is the same command used to restore a cursor position.  When you restore a frame configuration, any existing frames not included in the configuration become invisible.  If you wish to delete these frames instead, use C-u C-x r j r.



### 15.5 Keeping Numbers in Registers



There are commands to store a number in a register, to insert the number in the buffer in decimal, and to increment it.  These commands can be useful in keyboard macros (see [Keyboard Macros](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macros.html)).

- C-u number C-x r n r[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Number-Registers.html#index-C_002dx-r-n)

  Store number into register r (`number-to-register`).

- C-u number C-x r + r[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Number-Registers.html#index-C_002dx-r-_002b)

  If r contains a number, increment the number in that register by number.  Note that command C-x r + (`increment-register`) behaves differently if r contains text.  See [Saving Text in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Registers.html).

- C-x r i r

  Insert the number from register r into the buffer.

C-x r i is the same command used to insert any other sort of register contents into the buffer.  C-x r + with no numeric argument increments the register value by 1; C-x r n with no numeric argument stores zero in the register.

### 15.6 Keeping File and Buffer Names in Registers



If you visit certain file names frequently, you can visit them more conveniently if you put their names in registers.  Here’s the Lisp code used to put a file name into register r:

```
(set-register r '(file . name))
```

For example,

```
(set-register ?z '(file . "/gd/gnu/emacs/19.0/src/ChangeLog"))
```

puts the file name shown in register ‘z’.

To visit the file whose name is in register r, type C-x r j r.  (This is the same command used to jump to a position or restore a frame configuration.)

Similarly, if there are certain buffers you visit frequently, you can put their names in registers.  For instance, if you visit the ‘*Messages*’ buffer often, you can use the following snippet to put that buffer into the ‘m’ register:

```
(set-register ?m '(buffer . "*Messages*"))
```

To switch to the buffer whose name is in register r, type C-x r j r.



### 15.7 Keyboard Macro Registers



If you need to execute a keyboard macro (see [Keyboard Macros](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macros.html)) frequently, it is more convenient to put it in a register or save it (see [Naming and Saving Keyboard Macros](https://www.gnu.org/software/emacs/manual/html_node/emacs/Save-Keyboard-Macro.html)).  C-x C-k x r (`kmacro-to-register`) stores the last keyboard macro in register r.

To execute the keyboard macro in register r, type C-x r j r.  (This is the same command used to jump to a position or restore a frameset.)



### 15.8 Bookmarks



*Bookmarks* are somewhat like registers in that they record positions you can jump to.  Unlike registers, they have long names, and they persist automatically from one Emacs session to the next.  The prototypical use of bookmarks is to record where you were reading in various files.

- C-x r m RET

  Set the bookmark for the visited file, at point.

- C-x r m bookmark RET

  Set the bookmark named bookmark at point (`bookmark-set`).

- C-x r M bookmark RET

  Like C-x r m, but don’t overwrite an existing bookmark.

- C-x r b bookmark RET

  Jump to the bookmark named bookmark (`bookmark-jump`).

- C-x r l

  List all bookmarks (`list-bookmarks`).

- M-x bookmark-save

  Save all the current bookmark values in the default bookmark file.



To record the current position in the visited file, use the command C-x r m, which sets a bookmark using the visited file name as the default for the bookmark name.  If you name each bookmark after the file it points to, then you can conveniently revisit any of those files with C-x r b (`bookmark-jump`), and move to the position of the bookmark at the same time.



In addition to recording the current position, on graphical displays C-x r m places a special image on the left fringe (see [Window Fringes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fringes.html)) of the screen line corresponding to the recorded position, to indicate that there’s a bookmark there.  This can be controlled by the user option `bookmark-fringe-mark`: customize it to `nil` to disable the fringe mark.  The default value is `bookmark-mark`, which is the bitmap used for this purpose.  When you later use C-x r b to jump back to the bookmark, the fringe mark will be again shown on the fringe.



The command C-x r M (`bookmark-set-no-overwrite`) works like C-x r m, but it signals an error if the specified bookmark already exists, instead of overwriting it.



To display a list of all your bookmarks in a separate buffer, type C-x r l (`list-bookmarks`).  If you switch to that buffer, you can use it to edit your bookmark definitions or annotate the bookmarks.  Type C-h m in the bookmark buffer for more information about its special editing commands.



When you kill Emacs, Emacs saves your bookmarks, if you have changed any bookmark values.  You can also save the bookmarks at any time with the M-x bookmark-save command.  Bookmarks are saved to the file ~/.emacs.d/bookmarks (for compatibility with older versions of Emacs, if you have a file named ~/.emacs.bmk, that is used instead).  The bookmark commands load your default bookmark file automatically.  This saving and loading is how bookmarks persist from one Emacs session to the next.



If you set the variable `bookmark-save-flag` to 1, each command that sets a bookmark will also save your bookmarks; this way, you don’t lose any bookmark values even if Emacs crashes.  The value, if a number, says how many bookmark modifications should go by between saving.  If you set this variable to `nil`, Emacs only saves bookmarks if you explicitly use M-x bookmark-save.



The variable `bookmark-default-file` specifies the file in which to save bookmarks by default.



If you set the variable `bookmark-use-annotations` to `t`, setting a bookmark will query for an annotation.  If a bookmark has an annotation, it is automatically shown in a separate window when you jump to the bookmark.



Bookmark position values are saved with surrounding context, so that `bookmark-jump` can find the proper position even if the file is modified slightly.  The variable `bookmark-search-size` says how many characters of context to record on each side of the bookmark’s position.  (In buffers that are visiting encrypted files, no context is saved in the bookmarks file no matter the value of this variable.)

Here are some additional commands for working with bookmarks:

- M-x bookmark-load RET filename RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bookmarks.html#index-bookmark_002dload)

  Load a file named filename that contains a list of bookmark values.  You can use this command, as well as `bookmark-write`, to work with other files of bookmark values in addition to your default bookmark file.

- M-x bookmark-write RET filename RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bookmarks.html#index-bookmark_002dwrite)

  Save all the current bookmark values in the file filename.

- M-x bookmark-delete RET bookmark RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bookmarks.html#index-bookmark_002ddelete)

  Delete the bookmark named bookmark.

- M-x bookmark-insert-location RET bookmark RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bookmarks.html#index-bookmark_002dinsert_002dlocation)

  Insert in the buffer the name of the file that bookmark bookmark points to.

- M-x bookmark-insert RET bookmark RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bookmarks.html#index-bookmark_002dinsert)

  Insert in the buffer the *contents* of the file that bookmark bookmark points to.



## 16 Controlling the Display

Since only part of a large buffer fits in the window, Emacs has to show only a part of it.  This chapter describes commands and variables that let you specify which part of the text you want to see, and how the text is displayed.



### 16.1 Scrolling



If a window is too small to display all the text in its buffer, it displays only a portion of it.  *Scrolling* commands change which portion of the buffer is displayed.

Scrolling forward or up advances the portion of the buffer displayed in the window; equivalently, it moves the buffer text upwards relative to the window.  Scrolling backward or down displays an earlier portion of the buffer, and moves the text downwards relative to the window.

In Emacs, scrolling up or down refers to the direction that the text moves in the window, *not* the direction that the window moves relative to the text.  This terminology was adopted by Emacs before the modern meaning of “scrolling up” and “scrolling down” became widespread.  Hence, the strange result that PageDown scrolls up in the Emacs sense.

The portion of a buffer displayed in a window always contains point. If you move point past the bottom or top of the window, scrolling occurs automatically to bring it back onscreen (see [Automatic Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Scrolling.html)).  You can also scroll explicitly with these commands:

- C-v

- PageDown

- next

  Scroll forward by nearly a full window (`scroll-up-command`).

- M-v

- PageUp

- prior

  Scroll backward (`scroll-down-command`).



C-v (`scroll-up-command`) scrolls forward by nearly the whole window height.  The effect is to take the two lines at the bottom of the window and put them at the top, followed by lines that were not previously visible.  If point was in the text that scrolled off the top, it ends up on the window’s new topmost line.  The PageDown (or next) key is equivalent to C-v.

M-v (`scroll-down-command`) scrolls backward in a similar way.  The PageUp (or prior) key is equivalent to M-v.



The number of lines of overlap left by these scroll commands is controlled by the variable `next-screen-context-lines`, whose default value is 2.  You can supply the commands with a numeric prefix argument, n, to scroll by n lines; Emacs attempts to leave point unchanged, so that the text and point move up or down together. C-v with a negative argument is like M-v and vice versa.



By default, these commands signal an error (by beeping or flashing the screen) if no more scrolling is possible, because the window has reached the beginning or end of the buffer.  If you change the variable `scroll-error-top-bottom` to `t`, these commands move point to the farthest possible position.  If point is already there, the commands signal an error.



Some users like scroll commands to keep point at the same screen position, so that scrolling back to the same screen conveniently returns point to its original position.  You can enable this behavior via the variable `scroll-preserve-screen-position`.  If the value is `t`, Emacs adjusts point to keep the cursor at the same screen position whenever a scroll command moves it off-window, rather than moving it to the topmost or bottommost line.  With any other non-`nil` value, Emacs adjusts point this way even if the scroll command leaves point in the window.  This variable affects all the scroll commands documented in this section, as well as scrolling with the mouse wheel (see [Mouse Commands for Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mouse-Commands.html)); in general, it affects any command that has a non-`nil` `scroll-command` property. See [Property Lists](https://www.gnu.org/software/emacs/manual/html_node/elisp/Property-Lists.html#Property-Lists) in The Emacs Lisp Reference Manual.  The same property also causes Emacs not to exit incremental search when one of these commands is invoked and `isearch-allow-scroll` is non-`nil` (see [Not Exiting Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Not-Exiting-Isearch.html)).



Sometimes, particularly when you hold down keys such as C-v and M-v, activating keyboard auto-repeat, Emacs fails to keep up with the rapid rate of scrolling requested; the display doesn’t update and Emacs can become unresponsive to input for quite a long time.  You can counter this sluggishness by setting the variable `fast-but-imprecise-scrolling` to a non-`nil` value.  This instructs the scrolling commands not to fontify (see [Font Lock mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Font-Lock.html)) any unfontified text they scroll over, instead to assume it has the default face.  This can cause Emacs to scroll to somewhat wrong buffer positions when the faces in use are not all the same size, even with single (i.e., without auto-repeat) scrolling operations.



As an alternative to setting `fast-but-imprecise-scrolling` you might prefer to enable jit-lock deferred fontification (see [Font Lock mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Font-Lock.html)).  To do this, customize `jit-lock-defer-time` to a small positive number such as 0.25, or even 0.1 if you type quickly.  This gives you less jerky scrolling when you hold down C-v, but the window contents after any action which scrolls into a fresh portion of the buffer will be momentarily unfontified.



Finally, a third alternative to these variables is `redisplay-skip-fontification-on-input`.  If this variable is non-`nil`, skip some fontifications if there’s input pending. This usually does not affect the display because redisplay is completely skipped anyway if input was pending, but it can make scrolling smoother by avoiding unnecessary fontification.



The commands M-x scroll-up and M-x scroll-down behave similarly to `scroll-up-command` and `scroll-down-command`, except they do not obey `scroll-error-top-bottom`.  Prior to Emacs 24, these were the default commands for scrolling up and down. The commands M-x scroll-up-line and M-x scroll-down-line scroll the current window by one line at a time.  If you intend to use any of these commands, you might want to give them key bindings (see [Rebinding Keys in Your Init File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-Rebinding.html)).

On graphical displays, you can also scroll a window using the scroll bar; see [Scroll Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scroll-Bars.html).



### 16.2 Recentering

- C-l

  Scroll the selected window so the current line is the center-most text line; on subsequent consecutive invocations, make the current line the top line, the bottom line, and so on in cyclic order.  Possibly redisplay the screen too (`recenter-top-bottom`).

- C-M-S-l

  Scroll the other window; this is equivalent to C-l acting on the other window.

- M-x recenter

  Scroll the selected window so the current line is the center-most text line.  Possibly redisplay the screen too.

- C-M-l

  Scroll heuristically to bring useful information onto the screen (`reposition-window`).



The C-l (`recenter-top-bottom`) command *recenters* the selected window, scrolling it so that the current screen line is exactly in the center of the window, or as close to the center as possible.

Typing C-l twice in a row (C-l C-l) scrolls the window so that point is on the topmost screen line.  Typing a third C-l scrolls the window so that point is on the bottom-most screen line. Each successive C-l cycles through these three positions.



You can change the cycling order by customizing the list variable `recenter-positions`.  Each list element should be the symbol `top`, `middle`, or `bottom`, or a number; an integer means to move the line to the specified screen line, while a floating-point number between 0.0 and 1.0 specifies a percentage of the screen space from the top of the window.  The default, `(middle top bottom)`, is the cycling order described above. Furthermore, if you change the variable `scroll-margin` to a non-zero value n, C-l always leaves at least n screen lines between point and the top or bottom of the window (see [Automatic Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Scrolling.html)).

You can also give C-l a prefix argument.  A plain prefix argument, C-u C-l, simply recenters the line showing point.  A positive argument n moves line showing point n lines down from the top of the window.  An argument of zero moves point’s line to the top of the window.  A negative argument −n moves point’s line n lines from the bottom of the window.  When given an argument, C-l does not clear the screen or cycle through different screen positions.



If the variable `recenter-redisplay` has a non-`nil` value, each invocation of C-l also clears and redisplays the screen; the special value `tty` (the default) says to do this on text-terminal frames only.  Redisplaying is useful in case the screen becomes garbled for any reason (see [Garbage on the Screen](https://www.gnu.org/software/emacs/manual/html_node/emacs/Screen-Garbled.html)).



The more primitive command M-x recenter behaves like `recenter-top-bottom`, but does not cycle among screen positions.



C-M-l (`reposition-window`) scrolls the current window heuristically in a way designed to get useful information onto the screen.  For example, in a Lisp file, this command tries to get the entire current defun onto the screen if possible.



### 16.3 Automatic Scrolling



Emacs performs *automatic scrolling* when point moves out of the visible portion of the text.  Normally, automatic scrolling centers point vertically in the window, but there are several ways to alter this behavior.



If you set `scroll-conservatively` to a small number n, then moving point just a little off the screen (no more than n lines) causes Emacs to scroll just enough to bring point back on screen; if doing so fails to make point visible, Emacs scrolls just far enough to center point in the window.  If you set `scroll-conservatively` to a large number (larger than 100), automatic scrolling never centers point, no matter how far point moves; Emacs always scrolls text just enough to bring point into view, either at the top or bottom of the window depending on the scroll direction.  By default, `scroll-conservatively` is 0, which means to always center point in the window. This said, in minibuffer windows, scrolling is always conservative by default because `scroll-minibuffer-conservatively` is non-`nil`, which takes precedence over `scroll-conservatively`.



Another way to control automatic scrolling is to customize the variable `scroll-step`.  Its value determines the number of lines by which to automatically scroll, when point moves off the screen.  If scrolling by that number of lines fails to bring point back into view, point is centered instead.  The default value is zero, which (by default) causes point to always be centered after scrolling.



A third way to control automatic scrolling is to customize the variables `scroll-up-aggressively` and `scroll-down-aggressively`, which directly specify the vertical position of point after scrolling.  The value of `scroll-up-aggressively` should be either `nil` (the default), or a floating point number f between 0 and 1.  The latter means that when point goes below the bottom window edge (i.e., scrolling forward), Emacs scrolls the window so that point is f parts of the window height from the bottom window edge.  Thus, larger f means more aggressive scrolling: more new text is brought into view.  The default value, `nil`, is equivalent to 0.5.

Likewise, `scroll-down-aggressively` is used when point goes above the top window edge (i.e., scrolling backward).  The value specifies how far point should be from the top margin of the window after scrolling.  Thus, as with `scroll-up-aggressively`, a larger value is more aggressive.

Note that the variables `scroll-conservatively`, `scroll-step`, and `scroll-up-aggressively` / `scroll-down-aggressively` control automatic scrolling in contradictory ways.  Therefore, you should pick no more than one of these methods to customize automatic scrolling.  In case you customize multiple variables, the order of priority is: `scroll-conservatively`, then `scroll-step`, and finally `scroll-up-aggressively` / `scroll-down-aggressively`.



The variable `scroll-margin` restricts how close point can come to the top or bottom of a window (even if aggressive scrolling specifies a fraction f that is larger than the window portion between the top and the bottom margins).  Its value is a number of screen lines; if point comes within that many lines of the top or bottom of the window, Emacs performs automatic scrolling.  By default, `scroll-margin` is 0.  The effective margin size is limited to a quarter of the window height by default, but this limit can be increased up to half (or decreased down to zero) by customizing `maximum-scroll-margin`.





### 16.4 Horizontal Scrolling



*Horizontal scrolling* means shifting all the lines sideways within a window, so that some of the text near the left margin is not displayed.  When the text in a window is scrolled horizontally, text lines are truncated rather than continued (see [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html)). If a window shows truncated lines, Emacs performs automatic horizontal scrolling whenever point moves off the left or right edge of the screen.  By default, all the lines in the window are scrolled horizontally together, but if you set the variable `auto-hscroll-mode` to the special value of `current-line`, only the line showing the cursor will be scrolled.  To disable automatic horizontal scrolling entirely, set the variable `auto-hscroll-mode` to `nil`.  Note that when the automatic horizontal scrolling is turned off, if point moves off the edge of the screen, the cursor disappears to indicate that.  (On text terminals, the cursor is left at the edge instead.)



The variable `hscroll-margin` controls how close point can get to the window’s left and right edges before automatic scrolling occurs.  It is measured in columns.  For example, if the value is 5, then moving point within 5 columns of an edge causes horizontal scrolling away from that edge.



The variable `hscroll-step` determines how many columns to scroll the window when point gets too close to the edge.  Zero, the default value, means to center point horizontally within the window. A positive integer value specifies the number of columns to scroll by. A floating-point number (whose value should be between 0 and 1) specifies the fraction of the window’s width to scroll by.

You can also perform explicit horizontal scrolling with the following commands:

- C-x <

  Scroll text in current window to the left (`scroll-left`).

- C-x >

  Scroll to the right (`scroll-right`).



C-x < (`scroll-left`) scrolls text in the selected window to the left by the full width of the window, less two columns.  (In other words, the text in the window moves left relative to the window.)  With a numeric argument n, it scrolls by n columns.

If the text is scrolled to the left, and point moves off the left edge of the window, the cursor will freeze at the left edge of the window, until point moves back to the displayed portion of the text. This is independent of the current setting of `auto-hscroll-mode`, which, for text scrolled to the left, only affects the behavior at the right edge of the window.

C-x > (`scroll-right`) scrolls similarly to the right. The window cannot be scrolled any farther to the right once it is displayed normally, with each line starting at the window’s left margin; attempting to do so has no effect.  This means that you don’t have to calculate the argument precisely for C-x >; any sufficiently large argument will restore the normal display.

If you use those commands to scroll a window horizontally, that sets a lower bound for automatic horizontal scrolling.  Automatic scrolling will continue to scroll the window, but never farther to the right than the amount you previously set by `scroll-left`.  When `auto-hscroll-mode` is set to `current-line`, all the lines other than the one showing the cursor will be scrolled by that minimal amount.

On graphical displays, you can scroll a window horizontally using the horizontal scroll bar, if you turn on the optional `horizontal-scroll-bar-mode`; see [Scroll Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scroll-Bars.html).



### 16.5 Narrowing



*Narrowing* means focusing in on some portion of the buffer, making the rest temporarily inaccessible.  The portion which you can still get to is called the *accessible portion*.  Canceling the narrowing, which makes the entire buffer once again accessible, is called *widening*.  The bounds of narrowing in effect in a buffer are called the buffer’s *restriction*.

Narrowing can make it easier to concentrate on a single subroutine or paragraph by eliminating clutter.  It can also be used to limit the range of operation of a replace command or repeating keyboard macro.

- C-x n n

  Narrow down to between point and mark (`narrow-to-region`).

- C-x n w

  Widen to make the entire buffer accessible again (`widen`).

- C-x n p

  Narrow down to the current page (`narrow-to-page`).

- C-x n d

  Narrow down to the current defun (`narrow-to-defun`).

When you have narrowed down to a part of the buffer, that part appears to be all there is.  You can’t see the rest, you can’t move into it (motion commands won’t go outside the accessible part), you can’t change it in any way.  However, it is not gone, and if you save the file all the inaccessible text will be saved.  The word ‘Narrow’ appears in the mode line whenever narrowing is in effect.



The primary narrowing command is C-x n n (`narrow-to-region`). It sets the current buffer’s restrictions so that the text in the current region remains accessible, but all text before the region or after the region is inaccessible.  Point and mark do not change.



Alternatively, use C-x n p (`narrow-to-page`) to narrow down to the current page.  See [Pages](https://www.gnu.org/software/emacs/manual/html_node/emacs/Pages.html), for the definition of a page. C-x n d (`narrow-to-defun`) narrows down to the defun containing point (see [Top-Level Definitions, or Defuns](https://www.gnu.org/software/emacs/manual/html_node/emacs/Defuns.html)).



The way to cancel narrowing is to widen with C-x n w (`widen`).  This makes all text in the buffer accessible again.

You can get information on what part of the buffer you are narrowed down to using the C-x = command.  See [Cursor Position Information](https://www.gnu.org/software/emacs/manual/html_node/emacs/Position-Info.html).

Because narrowing can easily confuse users who do not understand it, `narrow-to-region` is normally a disabled command.  Attempting to use this command asks for confirmation and gives you the option of enabling it; if you enable the command, confirmation will no longer be required for it.  See [Disabling Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabling.html).



### 16.6 View Mode



View mode is a minor mode that lets you scan a buffer by sequential screenfuls.  It provides commands for scrolling through the buffer conveniently but not for changing it.  Apart from the usual Emacs cursor motion commands, you can type SPC to scroll forward one windowful, S-SPC or DEL to scroll backward, and s to start an incremental search.



Typing q (`View-quit`) disables View mode, and switches back to the buffer and position before View mode was enabled.  Typing e (`View-exit`) disables View mode, keeping the current buffer and position.



M-x view-buffer prompts for an existing Emacs buffer, switches to it, and enables View mode.  M-x view-file prompts for a file and visits it with View mode enabled.



### 16.7 Follow Mode



*Follow mode* is a minor mode that makes two windows, both showing the same buffer, scroll as a single tall virtual window. To use Follow mode, go to a frame with just one window, split it into two side-by-side windows using C-x 3, and then type M-x follow-mode.  From then on, you can edit the buffer in either of the two windows, or scroll either one; the other window follows it.

In Follow mode, if you move point outside the portion visible in one window and into the portion visible in the other window, that selects the other window—again, treating the two as if they were parts of one large window.

To turn off Follow mode, type M-x follow-mode a second time.



### 16.8 Text Faces



Emacs can display text in several different styles, called *faces*.  Each face can specify various *face attributes*, such as the font, height, weight, slant, foreground and background color, and underlining or overlining.  Most major modes assign faces to the text automatically, via Font Lock mode.  See [Font Lock mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Font-Lock.html), for more information about how these faces are assigned.



To see what faces are currently defined, and what they look like, type M-x list-faces-display.  With a prefix argument, this prompts for a regular expression, and displays only faces with names matching that regular expression (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)).



It’s possible for a given face to look different in different frames.  For instance, some text terminals do not support all face attributes, particularly font, height, and width, and some support a limited range of colors.  In addition, most Emacs faces are defined so that their attributes are different on light and dark frame backgrounds, for reasons of legibility.  By default, Emacs automatically chooses which set of face attributes to display on each frame, based on the frame’s current background color.  However, you can override this by giving the variable `frame-background-mode` a non-`nil` value.  A value of `dark` makes Emacs treat all frames as if they have a dark background, whereas a value of `light` makes it treat all frames as if they have a light background.



You can customize a face to alter its attributes, and save those customizations for future Emacs sessions.  See [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html), for details.

The `default` face is the default for displaying text, and all of its attributes are specified.  Its background color is also used as the frame’s background color.  See [Colors for Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Colors.html).



Another special face is the `cursor` face.  On graphical displays, the background color of this face is used to draw the text cursor.  None of the other attributes of this face have any effect; the foreground color for text under the cursor is taken from the background color of the underlying text.  On text terminals, the appearance of the text cursor is determined by the terminal, not by the `cursor` face.

You can also use X resources to specify attributes of any particular face.  See [X Resources](https://www.gnu.org/software/emacs/manual/html_node/emacs/Resources.html).

Emacs can display variable-width fonts, but some Emacs commands, particularly indentation commands, do not account for variable character display widths.  Therefore, we recommend not using variable-width fonts for most faces, particularly those assigned by Font Lock mode.



### 16.9 Colors for Faces

Faces can have various foreground and background colors.  When you specify a color for a face—for instance, when customizing the face (see [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html))—you can use either a *color name* or an *RGB triplet*.

- [Color Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Colors.html#Color-Names)
- [RGB Triplets](https://www.gnu.org/software/emacs/manual/html_node/emacs/Colors.html#RGB-Triplets)

#### 16.9.1 Color Names



A color name is a pre-defined name, such as ‘dark orange’ or ‘medium sea green’.  To view a list of color names, type M-x list-colors-display.  To control the order in which colors are shown, customize `list-colors-sort`.  If you run this command on a graphical display, it shows the full range of color names known to Emacs (these are the standard X11 color names, defined in X’s rgb.txt file).  If you run the command on a text terminal, it shows only a small subset of colors that can be safely displayed on such terminals.  However, Emacs understands X11 color names even on text terminals; if a face is given a color specified by an X11 color name, it is displayed using the closest-matching terminal color.

#### 16.9.2 RGB Triplets



An RGB triplet is a string of the form ‘#RRGGBB’.  Each of the primary color components is represented by a hexadecimal number between ‘00’ (intensity 0) and ‘FF’ (the maximum intensity). It is also possible to use one, three, or four hex digits for each component, so ‘red’ can be represented as ‘#F00’, ‘#fff000000’, or ‘#ffff00000000’.  The components must have the same number of digits.  For hexadecimal values A to F, either upper or lower case are acceptable.

The M-x list-colors-display command also shows the equivalent RGB triplet for each named color.  For instance, ‘medium sea green’ is equivalent to ‘#3CB371’.



You can change the foreground and background colors of a face with M-x set-face-foreground and M-x set-face-background. These commands prompt in the minibuffer for a face name and a color, with completion, and then set that face to use the specified color. They affect the face colors on all frames, but their effects do not persist for future Emacs sessions, unlike using the customization buffer or X resources.  You can also use frame parameters to set foreground and background colors for a specific frame; See [Frame Parameters](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frame-Parameters.html).

### 16.10 Standard Faces



Here are the standard faces for specifying text appearance.  You can apply them to specific text when you want the effects they produce.

- `default`

  This face is used for ordinary text that doesn’t specify any face. Its background color is used as the frame’s background color.

- `bold`

  This face uses a bold variant of the default font.

- `italic`

  This face uses an italic variant of the default font.

- `bold-italic`

  This face uses a bold italic variant of the default font.

- `underline`

  This face underlines text.

- `fixed-pitch`

  This face forces use of a fixed-width font.  It’s reasonable to customize this face to use a different fixed-width font, if you like, but you should not make it a variable-width font.

- `fixed-pitch-serif`

  This face is like `fixed-pitch`, except the font has serifs and looks more like traditional typewriting.

- `variable-pitch`

  This face forces use of a variable-width (i.e., proportional) font. The font size picked for this face matches the font picked for the default (usually fixed-width) font.

- `variable-pitch-text`

  This is like the `variable-pitch` face (from which it inherits), but is slightly larger.  A proportional font of the same height as a monospace font usually appears visually smaller, and can therefore be harder to read.  When displaying longer texts, this face can be a good choice over the (slightly smaller) `variable-pitch` face.

- `shadow`

  This face is used for making the text less noticeable than the surrounding ordinary text.  Usually this can be achieved by using shades of gray in contrast with either black or white default foreground color.

Here’s an incomplete list of faces used to highlight parts of the text temporarily for specific purposes.  (Many other modes define their own faces for this purpose.)

- `highlight`

  This face is used for text highlighting in various contexts, such as when the mouse cursor is moved over a hyperlink.

- `isearch`

  This face is used to highlight the current Isearch match (see [Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Incremental-Search.html)).

- `query-replace`

  This face is used to highlight the current Query Replace match (see [Replacement Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Replace.html)).

- `lazy-highlight`

  This face is used to highlight lazy matches for Isearch and Query Replace (matches other than the current one).

- `region`

  This face is used for displaying an active region (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)). When Emacs is built with GTK+ support, its colors are taken from the current GTK+ theme.

- `secondary-selection`

  This face is used for displaying a secondary X selection (see [Secondary Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Secondary-Selection.html)).

- `trailing-whitespace`

  The face for highlighting excess spaces and tabs at the end of a line when `show-trailing-whitespace` is non-`nil` (see [Useless Whitespace](https://www.gnu.org/software/emacs/manual/html_node/emacs/Useless-Whitespace.html)).

- `escape-glyph`

  The face for displaying control characters and escape sequences (see [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html)).

- `homoglyph`

  The face for displaying lookalike characters, i.e., characters that look like but are not the characters being represented (see [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html)).

- `nobreak-space`

  The face for displaying no-break space characters (see [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html)).

- `nobreak-hyphen`

  The face for displaying no-break hyphen characters (see [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html)).

The following faces control the appearance of parts of the Emacs frame:

- `mode-line`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-mode_002dline-face)

  This is the base face used for the mode lines, as well as header lines and for menu bars when toolkit menus are not used.  By default, it’s drawn with shadows for a raised effect on graphical displays, and drawn as the inverse of the default face on text terminals. The `mode-line-active` and `mode-line-inactive` faces (which are the ones used on the mode lines) inherit from this face.

- `mode-line-active`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-faces-for-mode-lines)

  Like `mode-line`, but used for the mode line of the currently selected window.  This face inherits from `mode-line`, so changes in that face affect mode lines in all windows.

- `mode-line-inactive`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-mode_002dline_002dinactive-face)

  Like `mode-line`, but used for mode lines of the windows other than the selected one (if `mode-line-in-non-selected-windows` is non-`nil`).  This face inherits from `mode-line`, so changes in that face affect mode lines in all windows.

- `mode-line-highlight`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-mode_002dline_002dhighlight-face)

  Like `highlight`, but used for mouse-sensitive portions of text on mode lines.  Such portions of text typically pop up tooltips (see [Tooltips](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tooltips.html)) when the mouse pointer hovers above them.

- `mode-line-buffer-id`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-mode_002dline_002dbuffer_002did-face)

  This face is used for buffer identification parts in the mode line.

- `header-line`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-header_002dline-face)

  Similar to `mode-line` for a window’s header line, which appears at the top of a window just as the mode line appears at the bottom. Most windows do not have a header line—only some special modes, such Info mode, create one.

- `header-line-highlight`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-header_002dline_002dhighlight-face)

  Similar to `highlight` and `mode-line-highlight`, but used for mouse-sensitive portions of text on header lines.  This is a separate face because the `header-line` face might be customized in a way that does not interact well with `highlight`.

- `tab-line`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-tab_002dline-face)

  Similar to `mode-line` for a window’s tab line, which appears at the top of a window with tabs representing window buffers. See [Window Tab Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Line.html).

- `vertical-border`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-vertical_002dborder-face)

  This face is used for the vertical divider between windows on text terminals.

- `minibuffer-prompt`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-minibuffer_002dprompt-face)

  This face is used for the prompt strings displayed in the minibuffer. By default, Emacs automatically adds this face to the value of `minibuffer-prompt-properties`, which is a list of text properties (see [Text Properties](https://www.gnu.org/software/emacs/manual/html_node/elisp/Text-Properties.html#Text-Properties) in the Emacs Lisp Reference Manual) used to display the prompt text.  (This variable takes effect when you enter the minibuffer.)

- `fringe`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-fringe-face)

  The face for the fringes to the left and right of windows on graphic displays.  (The fringes are the narrow portions of the Emacs frame between the text area and the window’s right and left borders.) See [Window Fringes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fringes.html).

- `cursor`

  The `:background` attribute of this face specifies the color of the text cursor.  See [Displaying the Cursor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cursor-Display.html).

- `tooltip`

  This face is used for tooltip text.  By default, if Emacs is built with GTK+ support, tooltips are drawn via GTK+ and this face has no effect.  See [Tooltips](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tooltips.html).

- `mouse`

  This face determines the color of the mouse pointer.

The following faces likewise control the appearance of parts of the Emacs frame, but only on text terminals, or when Emacs is built on X with no toolkit support.  (For all other cases, the appearance of the respective frame elements is determined by system-wide settings.)

- `scroll-bar`

  This face determines the visual appearance of the scroll bar. See [Scroll Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scroll-Bars.html).

- `tool-bar`

  This face determines the color of tool bar icons.  See [Tool Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tool-Bars.html).

- `tab-bar`

  This face determines the color of tab bar icons.  See [Tab Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Bars.html).

- `menu`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-menu-bar-appearance)

  This face determines the colors and font of Emacs’s menus.  See [Menu Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Menu-Bars.html).

- `tty-menu-enabled-face`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Standard-Faces.html#index-faces-for-text_002dmode-menus)

  This face is used to display enabled menu items on text-mode terminals.

- `tty-menu-disabled-face`

  This face is used to display disabled menu items on text-mode terminals.

- `tty-menu-selected-face`

  This face is used to display on text-mode terminals the menu item that would be selected if you click a mouse or press RET.



### 16.11 Icons



Emacs sometimes displays clickable buttons (or other informative icons), and you can customize how these look on display.



The main customization point here is the `icon-preference` user option.  By using this, you can tell Emacs your overall preferences for icons.  This is a list of icon types, and the first icon type that’s supported will be used.  The supported types are:

- `image`

  Use an image for the icon.

- `emoji`

  Use a colorful emoji for the icon.

- `symbol`

  Use a monochrome symbol for the icon.

- `text`

  Use a simple text for the icon.

In addition, each individual icon can be customized with M-x customize-icon, and themes can further alter the looks of the icons.

To get a quick description of an icon, use the M-x describe-icon command.

### 16.12 Text Scale



To increase the font size of the `default` face in the current buffer, type C-x C-+ or C-x C-=.  To decrease it, type C-x C--.  To restore the default (global) font size, type C-x C-0.  These keys are all bound to the same command, `text-scale-adjust`, which looks at the last key typed to determine which action to take and adjusts the font size accordingly by changing the height of the default face.

Most faces don’t have an explicit setting of the `:height` attribute, and thus inherit the height from the `default` face. Those faces are also scaled by the above commands.

Faces other than `default` that have an explicit setting of the `:height` attribute are not affected by these font size changes. The `header-line` face is an exception: it will be scaled even if it has an explicit setting of the `:height` attribute.

Similarly, scrolling the mouse wheel with the Ctrl modifier pressed, when the mouse pointer is above buffer text, will increase or decrease the font size of the affected faces, depending on the direction of the scrolling.

The final key of these commands may be repeated without the leading C-x and without the modifiers.  For instance, C-x C-= C-= C-= and C-x C-= = = increase the face height by three steps.  Each step scales the text height by a factor of 1.2; to change this factor, customize the variable `text-scale-mode-step`.  A numeric argument of 0 to the `text-scale-adjust` command restores the default height, the same as typing C-x C-0.



Similarly, to change the sizes of the fonts globally, type C-x C-M-+, C-x C-M-=, C-x C-M-- or C-x C-M-0, or scroll the mouse wheel with both the Ctrl and Meta modifiers pressed.  To enable frame resizing when the font size is changed globally, customize the variable `global-text-scale-adjust-resizes-frames` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).



The commands `text-scale-increase` and `text-scale-decrease` increase or decrease the size of the font in the current buffer, just like C-x C-+ and C-x C-- respectively.  You may find it convenient to bind to these commands, rather than `text-scale-adjust`.



The command `text-scale-set` scales the size of the font in the current buffer to an absolute level specified by its prefix argument.



The above commands automatically enable the minor mode `text-scale-mode` if the current font scaling is other than 1, and disable it otherwise.



The command `text-scale-pinch` increases or decreases the text scale based on the distance between fingers on a touchpad when a pinch gesture is performed by placing two fingers on a touchpad and moving them towards or apart from each other.  This is only available on some systems with supported hardware.



The command `mouse-wheel-text-scale` also changes the text scale.  Normally, it is run when you press Ctrl while moving the mouse wheel.  The text scale is increased when the wheel is moved downwards, and it is decreased when the wheel is moved upwards.



### 16.13 Font Lock mode



Font Lock mode is a minor mode, always local to a particular buffer, which assigns faces to (or *fontifies*) the text in the buffer. Each buffer’s major mode tells Font Lock mode which text to fontify; for instance, programming language modes fontify syntactically relevant constructs like comments, strings, and function names.



Font Lock mode is enabled by default in major modes that support it. To toggle it in the current buffer, type M-x font-lock-mode.  A positive numeric argument unconditionally enables Font Lock mode, and a negative or zero argument disables it.



Type M-x global-font-lock-mode to toggle Font Lock mode in all buffers.  To impose this setting for future Emacs sessions, customize the variable `global-font-lock-mode` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)), or add the following line to your init file:

```
(global-font-lock-mode 0)
```

If you have disabled Global Font Lock mode, you can still enable Font Lock for specific major modes by adding the function `font-lock-mode` to the mode hooks (see [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html)).  For example, to enable Font Lock mode for editing C files, you can do this:

```
(add-hook 'c-mode-hook 'font-lock-mode)
```

Font Lock mode uses several specifically named faces to do its job, including `font-lock-string-face`, `font-lock-comment-face`, and others.  The easiest way to find them all is to use M-x customize-group RET font-lock-faces RET.  You can then use that customization buffer to customize the appearance of these faces.  See [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html).



Fontifying very large buffers can take a long time.  To avoid large delays when a file is visited, Emacs initially fontifies only the visible portion of a buffer.  As you scroll through the buffer, each portion that becomes visible is fontified as soon as it is displayed; this type of Font Lock is called *Just-In-Time* (or *JIT*) Lock.  You can control how JIT Lock behaves, including telling it to perform fontification while idle, by customizing variables in the customization group ‘jit-lock’.  See [Customizing Specific Items](https://www.gnu.org/software/emacs/manual/html_node/emacs/Specific-Customization.html).

The information that major modes use for determining which parts of buffer text to fontify and what faces to use can be based on several different ways of analyzing the text:

- Search for keywords and other textual patterns based on regular expressions (see [Regular Expression Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Search.html)).
- Find syntactically distinct parts of text based on built-in syntax tables (see [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual).
- Use syntax tree produced by a full-blown parser, via a special-purpose library, such as the tree-sitter library (see [Parsing Program Source](https://www.gnu.org/software/emacs/manual/html_node/elisp/Parsing-Program-Source.html#Parsing-Program-Source) in The Emacs Lisp Reference Manual), or an external program.

- [Traditional Font Lock](https://www.gnu.org/software/emacs/manual/html_node/emacs/Traditional-Font-Lock.html)
- [Parser-based Font Lock](https://www.gnu.org/software/emacs/manual/html_node/emacs/Parser_002dbased-Font-Lock.html)



#### 16.13.1 Traditional Font Lock



“Traditional” methods of providing font-lock information are based on regular-expression search and on syntactic analysis using syntax tables built into Emacs.  This subsection describes the use and customization of font-lock for major modes which use these traditional methods.



You can control the amount of fontification applied by Font Lock mode by customizing the variable `font-lock-maximum-decoration`, for major modes that support this feature.  The value of this variable should be a number (with 1 representing a minimal amount of fontification; some modes support levels as high as 3); or `t`, meaning “as high as possible” (the default).  To be effective for a given file buffer, the customization of `font-lock-maximum-decoration` should be done *before* the file is visited; if you already have the file visited in a buffer when you customize this variable, kill the buffer and visit the file again after the customization.

You can also specify different numbers for particular major modes; for example, to use level 1 for C/C++ modes, and the default level otherwise, use the value

```
'((c-mode . 1) (c++-mode . 1)))
```



Comment and string fontification (or “syntactic” fontification) relies on analysis of the syntactic structure of the buffer text.  For the sake of speed, some modes, including Lisp mode, rely on a special convention: an open-parenthesis or open-brace in the leftmost column always defines the beginning of a defun, and is thus always outside any string or comment.  Therefore, you should avoid placing an open-parenthesis or open-brace in the leftmost column, if it is inside a string or comment.  See [Left Margin Convention](https://www.gnu.org/software/emacs/manual/html_node/emacs/Left-Margin-Paren.html), for details.



Font Lock highlighting patterns already exist for most modes, but you may want to fontify additional patterns.  You can use the function `font-lock-add-keywords`, to add your own highlighting patterns for a particular mode.  For example, to highlight ‘FIXME:’ words in C comments, use this:

```
(add-hook 'c-mode-hook
          (lambda ()
           (font-lock-add-keywords nil
            '(("\\<\\(FIXME\\):" 1
               font-lock-warning-face t)))))
```



To remove keywords from the font-lock highlighting patterns, use the function `font-lock-remove-keywords`.  See [Search-based Fontification](https://www.gnu.org/software/emacs/manual/html_node/elisp/Search_002dbased-Fontification.html#Search_002dbased-Fontification) in The Emacs Lisp Reference Manual. Alternatively, you can selectively disable highlighting due to some keywords by customizing the `font-lock-ignore` option, see [Customizing Keywords](https://www.gnu.org/software/emacs/manual/html_node/elisp/Customizing-Keywords.html#Customizing-Keywords) in The Emacs Lisp Reference Manual.



#### 16.13.2 Parser-based Font Lock



If your Emacs was built with the tree-sitter library, it can use the results of parsing the buffer text by that library for the purposes of fontification.  This is usually faster and more accurate than the “traditional” methods described in the previous subsection, since the tree-sitter library provides full-blown parsers for programming languages and other kinds of formatted text which it supports.  Major modes which utilize the tree-sitter library are named `foo-ts-mode`, with the ‘-ts-’ part indicating the use of the library.  This subsection documents the Font Lock support based on the tree-sitter library.



You can control the amount of fontification applied by Font Lock mode of major modes based on tree-sitter by customizing the variable `treesit-font-lock-level`.  Its value is a number between 1 and 4:

- Level 1

  This level usually fontifies only comments and function names in function definitions.

- Level 2

  This level adds fontification of keywords, strings, and data types.

- Level 3

  This is the default level; it adds fontification of assignments, numbers, etc.

- Level 4

  This level adds everything else that can be fontified: operators, delimiters, brackets, other punctuation, function names in function calls, property look ups, variables, etc.



What exactly constitutes each of the syntactical categories mentioned above depends on the major mode and the parser grammar used by tree-sitter for the major-mode’s language.  However, in general the categories follow the conventions of the programming language or the file format supported by the major mode.  The buffer-local value of the variable `treesit-font-lock-feature-list` holds the fontification features supported by a tree-sitter based major mode, where each sub-list shows the features provided by the corresponding fontification level.

Once you change the value of `treesit-font-lock-level` via M-x customize-variable (see [Customizing Specific Items](https://www.gnu.org/software/emacs/manual/html_node/emacs/Specific-Customization.html)), it takes effect immediately in all the existing buffers and for files you visit in the future in the same session.



### 16.14 Interactive Highlighting



Highlight Changes mode is a minor mode that *highlights* the parts of the buffer that were changed most recently, by giving that text a different face.  To enable or disable Highlight Changes mode, use M-x highlight-changes-mode.



Hi Lock mode is a minor mode that highlights text that matches regular expressions you specify.  For example, you can use it to highlight all the references to a certain variable in a program source file, highlight certain parts in a voluminous output of some program, or highlight certain names in an article.  To enable or disable Hi Lock mode, use the command M-x hi-lock-mode.  To enable Hi Lock mode for all buffers, use M-x global-hi-lock-mode or place `(global-hi-lock-mode 1)` in your .emacs file.

Hi Lock mode works like Font Lock mode (see [Font Lock mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Font-Lock.html)), except that you specify explicitly the regular expressions to highlight.  You can control them with the following commands.  (The key bindings below that begin with C-x w are deprecated in favor of the global M-s h bindings, and will be removed in some future Emacs version.)

- M-s h r regexp RET face RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-r)

- C-x w h regexp RET face RET

  Highlight text that matches regexp using face face (`highlight-regexp`).  The highlighting will remain as long as the buffer is loaded.  For example, to highlight all occurrences of the word “whim” using the default face (a yellow background), type M-s h r whim RET RET.  Any face can be used for highlighting, Hi Lock provides several of its own and these are pre-loaded into a list of default values.  While being prompted for a face use M-n and M-p to cycle through them.  A prefix numeric argument limits the highlighting to the corresponding subexpression.  Setting the option `hi-lock-auto-select-face` to a non-`nil` value causes this command (and other Hi Lock commands that read faces) to automatically choose the next face from the default list without prompting. You can use this command multiple times, specifying various regular expressions to highlight in different ways.

- M-s h u regexp RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-u)

- C-x w r regexp RET

  Unhighlight regexp (`unhighlight-regexp`).  If you invoke this from the menu, you select the expression to unhighlight from a list.  If you invoke this from the keyboard, you use the minibuffer. It will show the most recently added regular expression; use M-n to show the next older expression and M-p to select the next newer expression.  (You can also type the expression by hand, with completion.)  When the expression you want to unhighlight appears in the minibuffer, press RET to exit the minibuffer and unhighlight it.

- M-s h l regexp RET face RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-l)

- C-x w l regexp RET face RET

  Highlight entire lines containing a match for regexp, using face face (`highlight-lines-matching-regexp`).

- M-s h p phrase RET face RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-p)

- C-x w p phrase RET face RET

  Highlight matches of phrase, using face face (`highlight-phrase`).  phrase can be any regexp, but spaces will be replaced by matches to whitespace and initial lower-case letters will become case insensitive.

- M-s h .[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-_002e)

- C-x w .

  Highlight the symbol found near point, using the next available face (`highlight-symbol-at-point`).

- M-s h w[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-w)

- C-x w b

  Insert all the current highlighting regexp/face pairs into the buffer at point, with comment delimiters to prevent them from changing your program.  (This key binding runs the `hi-lock-write-interactive-patterns` command.) These patterns are extracted from the comments, if appropriate, if you invoke M-x hi-lock-find-patterns, or if you visit the file while Hi Lock mode is enabled (since that runs `hi-lock-find-patterns`).

- M-s h f[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html#index-M_002ds-h-f)

- C-x w i

  Extract regexp/face pairs from comments in the current buffer (`hi-lock-find-patterns`).  Thus, you can enter patterns interactively with `highlight-regexp`, store them into the file with `hi-lock-write-interactive-patterns`, edit them (perhaps including different faces for different parenthesized parts of the match), and finally use this command (`hi-lock-find-patterns`) to have Hi Lock highlight the edited patterns.  The variable `hi-lock-file-patterns-policy` controls whether Hi Lock mode should automatically extract and highlight patterns found in a file when it is visited.  Its value can be `nil` (never highlight), `ask` (query the user), or a function.  If it is a function, `hi-lock-find-patterns` calls it with the patterns as argument; if the function returns non-`nil`, the patterns are used.  The default is `ask`.  Note that patterns are always highlighted if you call `hi-lock-find-patterns` directly, regardless of the value of this variable.  Also, `hi-lock-find-patterns` does nothing if the current major mode’s symbol is a member of the list `hi-lock-exclude-modes`.



### 16.15 Window Fringes



On graphical displays, each Emacs window normally has narrow *fringes* on the left and right edges.  The fringes are used to display symbols that provide information about the text in the window. You can type M-x fringe-mode to toggle display of the fringes or to modify their width.  This command affects fringes in all frames; to modify fringes on the selected frame only, use M-x set-fringe-style.  You can make your changes to the fringes permanent by customizing the variable `fringe-mode`.

The most common use of the fringes is to indicate a continuation line (see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html)).  When one line of text is split into multiple screen lines, the left fringe shows a curving arrow for each screen line except the first, indicating that this is not the real beginning.  The right fringe shows a curving arrow for each screen line except the last, indicating that this is not the real end.  If the line’s direction is right-to-left (see [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html)), the meanings of the curving arrows in the fringes are swapped.

The fringes indicate line truncation (see [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html)) with short horizontal arrows meaning there’s more text on this line which is scrolled horizontally out of view.  Clicking the mouse on one of the arrows scrolls the display horizontally in the direction of the arrow.

The fringes can also indicate other things, such as buffer boundaries (see [Displaying Boundaries](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Boundaries.html)), unused lines near the end of the window (see [indicate-empty-lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Useless-Whitespace.html#indicate_002dempty_002dlines)), and where a program you are debugging is executing (see [Running Debuggers Under Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Debuggers.html)).



The fringe is also used for drawing the cursor, if the current line is exactly as wide as the window and point is at the end of the line. To disable this, change the variable `overflow-newline-into-fringe` to `nil`; this causes Emacs to continue or truncate lines that are exactly as wide as the window.

If you customize `fringe-mode` to remove the fringes on one or both sides of the window display, the features that display on the fringe are not available.  Indicators of line continuation and truncation are an exception: when fringes are not available, Emacs uses the leftmost and rightmost character cells to indicate continuation and truncation with special ASCII characters, see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html), and [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html).  This reduces the width available for displaying text on each line, because the character cells used for truncation and continuation indicators are reserved for that purpose.  Since buffer text can include bidirectional text, and thus both left-to-right and right-to-left paragraphs (see [Bidirectional Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bidirectional-Editing.html)), removing only one of the fringes still reserves two character cells, one on each side of the window, for truncation and continuation indicators, because these indicators are displayed on opposite sides of the window in right-to-left paragraphs.



### 16.16 Displaying Boundaries



Emacs can display an indication of the `fill-column` position (see [Explicit Fill Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fill-Commands.html)).  The fill-column indicator is a useful functionality especially in `prog-mode` and its descendants (see [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html)) to indicate the position of a specific column that has some special meaning for formatting the source code of a program.  This assumes the buffer uses a fixed-pitch font, where all the characters (with the possible exception of double-width characters) have the same width on display.  If the buffer uses variable-pitch fonts, the fill-column indicators on different lines might appear unaligned.

To activate the fill-column indication display, use the minor modes `display-fill-column-indicator-mode` and `global-display-fill-column-indicator-mode`, which enable the indicator locally or globally, respectively.

Alternatively, you can set the two buffer-local variables `display-fill-column-indicator` and `display-fill-column-indicator-character` to activate the indicator and control the character used for the indication.  Note that both variables must be non-`nil` for the indication to be displayed.  (Turning on the minor mode sets both these variables.)

There are 2 buffer local variables and a face to customize this mode:

- `display-fill-column-indicator-column`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Boundaries.html#index-display_002dfill_002dcolumn_002dindicator_002dcolumn)

  Specifies the column number where the indicator should be set.  It can take positive numerical values for the column, or the special value `t`, which means that the value of the variable `fill-column` will be used. Any other value disables the indicator.  The default value is `t`.

- `display-fill-column-indicator-character`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Boundaries.html#index-display_002dfill_002dcolumn_002dindicator_002dcharacter)

  Specifies the character used for the indicator.  This character can be any valid character including Unicode ones if the font supports them. The value `nil` disables the indicator.  When the mode is enabled through the functions `display-fill-column-indicator-mode` or `global-display-fill-column-indicator-mode`, they will use the character specified by this variable, if it is non-`nil`; otherwise Emacs will use the character U+2502 BOX DRAWINGS LIGHT VERTICAL, falling back to ‘|’ if U+2502 cannot be displayed.

- `fill-column-indicator`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Boundaries.html#index-fill_002dcolumn_002dindicator)

  Specifies the face used to display the indicator.  It inherits its default values from the face `shadow`, but without background color.  To change the indicator color, you need only set the foreground color of this face.



On graphical displays, Emacs can indicate the buffer boundaries in the fringes.  If you enable this feature, the first line and the last line are marked with angle images in the fringes.  This can be combined with up and down arrow images which say whether it is possible to scroll the window.

The buffer-local variable `indicate-buffer-boundaries` controls how the buffer boundaries and window scrolling is indicated in the fringes.  If the value is `left` or `right`, both angle and arrow bitmaps are displayed in the left or right fringe, respectively.

If value is an alist (see [Association Lists](https://www.gnu.org/software/emacs/manual/html_node/elisp/Association-Lists.html#Association-Lists) in the Emacs Lisp Reference Manual), each element `(indicator . position)` specifies the position of one of the indicators.  The indicator must be one of `top`, `bottom`, `up`, `down`, or `t` which specifies the default position for the indicators not present in the alist.  The position is one of `left`, `right`, or `nil` which specifies not to show this indicator.

For example, `((top . left) (t . right))` places the top angle bitmap in left fringe, the bottom angle bitmap in right fringe, and both arrow bitmaps in right fringe.  To show just the angle bitmaps in the left fringe, but no arrow bitmaps, use `((top .  left) (bottom . left))`.



### 16.17 Useless Whitespace



It is easy to leave unnecessary spaces at the end of a line, or empty lines at the end of a buffer, without realizing it.  In most cases, this *trailing whitespace* has no effect, but sometimes it can be a nuisance.



You can make trailing whitespace at the end of a line visible by setting the buffer-local variable `show-trailing-whitespace` to `t`.  Then Emacs displays trailing whitespace, using the face `trailing-whitespace`.

This feature does not apply when point is at the end of the line containing the whitespace.  Strictly speaking, that is trailing whitespace nonetheless, but displaying it specially in that case looks ugly while you are typing in new text.  In this special case, the location of point is enough to show you that the spaces are present.



Type M-x delete-trailing-whitespace to delete all trailing whitespace.  This command deletes all extra spaces at the end of each line in the buffer, and all empty lines at the end of the buffer; to ignore the latter, change the variable `delete-trailing-lines` to `nil`.  If the region is active, the command instead deletes extra spaces at the end of each line in the region.



On graphical displays, Emacs can indicate unused lines at the end of the window with a small image in the left fringe (see [Window Fringes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fringes.html)). The image appears for screen lines that do not correspond to any buffer text, so blank lines at the end of the buffer stand out because they lack this image.  To enable this feature, set the buffer-local variable `indicate-empty-lines` to a non-`nil` value.  You can enable or disable this feature for all new buffers by setting the default value of this variable, e.g., `(setq-default indicate-empty-lines t)`.



Whitespace mode is a buffer-local minor mode that lets you visualize many kinds of whitespace in the buffer, by either drawing the whitespace characters with a special face or displaying them as special glyphs.  To toggle this mode, type M-x whitespace-mode.  The kinds of whitespace visualized are determined by the list variable `whitespace-style`.  Individual elements in that list can be toggled on or off in the current buffer by typing M-x whitespace-toggle-options.  Here is a partial list of possible elements (see the variable’s documentation for the full list):

- `face`

  Enable all visualizations which use special faces.  This element has a special meaning: if it is absent from the list, none of the other visualizations take effect except `space-mark`, `tab-mark`, and `newline-mark`.

- `trailing`

  Highlight trailing whitespace.

- `tabs`

  Highlight tab characters.

- `spaces`

  Highlight space and non-breaking space characters.

- `lines`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Useless-Whitespace.html#index-whitespace_002dline_002dcolumn)

  Highlight lines longer than 80 columns.  To change the column limit, customize the variable `whitespace-line-column`.

- `newline`

  Highlight newlines.

- `missing-newline-at-eof`

  Highlight the final character if the buffer doesn’t end with a newline character.

- `empty`

  Highlight empty lines at the beginning and/or end of the buffer.

- `big-indent`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Useless-Whitespace.html#index-whitespace_002dbig_002dindent_002dregexp)

  Highlight too-deep indentation.  By default any sequence of at least 4 consecutive tab characters or 32 consecutive space characters is highlighted.  To change that, customize the regular expression `whitespace-big-indent-regexp`.

- `space-mark`

  Draw space and non-breaking characters with a special glyph.

- `tab-mark`

  Draw tab characters with a special glyph.

- `newline-mark`

  Draw newline characters with a special glyph.



Global Whitespace mode is a global minor mode that lets you visualize whitespace in all buffers.  To toggle individual features, use M-x global-whitespace-toggle-options.



### 16.18 Selective Display



Emacs has the ability to hide lines indented more than a given number of columns.  You can use this to get an overview of a part of a program.

To hide lines in the current buffer, type C-x $ (`set-selective-display`) with a numeric argument n.  Then lines with at least n columns of indentation disappear from the screen.  The only indication of their presence is that three dots (‘…’) appear at the end of each visible line that is followed by one or more hidden ones.

The commands C-n and C-p move across the hidden lines as if they were not there.

The hidden lines are still present in the buffer, and most editing commands see them as usual, so you may find point in the middle of the hidden text.  When this happens, the cursor appears at the end of the previous line, after the three dots.  If point is at the end of the visible line, before the newline that ends it, the cursor appears before the three dots.

To make all lines visible again, type C-x $ with no argument.



If you set the variable `selective-display-ellipses` to `nil`, the three dots do not appear at the end of a line that precedes hidden lines.  Then there is no visible indication of the hidden lines.  This variable becomes local automatically when set.

See also [Outline Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Mode.html) for another way to hide part of the text in a buffer.



### 16.19 Optional Mode Line Features



The buffer percentage pos indicates the percentage of the buffer above the top of the window.  You can additionally display the size of the buffer by typing M-x size-indication-mode to turn on Size Indication mode.  The size will be displayed immediately following the buffer percentage like this:

```
pos of size
```

Here size is the human readable representation of the number of characters in the buffer, which means that ‘k’ for 10^3, ‘M’ for 10^6, ‘G’ for 10^9, etc., are used to abbreviate.



The current line number of point appears in the mode line when Line Number mode is enabled.  Use the command M-x line-number-mode to turn this mode on and off; normally it is on.  The line number appears after the buffer percentage pos, with the letter ‘L’ to indicate what it is.



Similarly, you can display the current column number by turning on Column Number mode with M-x column-number-mode.  The column number is indicated by the letter ‘C’.  However, when both of these modes are enabled, the line and column numbers are displayed in parentheses, the line number first, rather than with ‘L’ and ‘C’.  For example: ‘(561,2)’.  See [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html), for more information about minor modes and about how to use these commands.



In Column Number mode, the displayed column number counts from zero starting at the left margin of the window.  If you would prefer for the displayed column number to count from one, you may set `column-number-indicator-zero-based` to `nil`.



If you have narrowed the buffer (see [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html)), the displayed line number is relative to the accessible portion of the buffer. Thus, it isn’t suitable as an argument to `goto-line`.  (The command `what-line` shows the line number relative to the whole file.)  You can use `goto-line-relative` command to move point to the line relative to the accessible portion of the narrowed buffer.



If the buffer is very large (larger than the value of `line-number-display-limit`), Emacs won’t compute the line number, because that would be too slow; therefore, the line number won’t appear on the mode-line.  To remove this limit, set `line-number-display-limit` to `nil`.



Line-number computation can also be slow if the lines in the buffer are too long.  For this reason, Emacs doesn’t display line numbers if the average width, in characters, of lines near point is larger than the value of `line-number-display-limit-width`.  The default value is 200 characters.



Emacs can optionally display the time and system load in all mode lines.  To enable this feature, type M-x display-time or customize the option `display-time-mode`.  The information added to the mode line looks like this:

```
hh:mmPM l.ll
```



Here hh and mm are the hour and minute, followed always by ‘AM’ or ‘PM’.  l.ll is the average number, collected for the last few minutes, of processes in the whole system that were either running or ready to run (i.e., were waiting for an available processor).  (Some fields may be missing if your operating system cannot support them.)  If you prefer time display in 24-hour format, set the variable `display-time-24hr-format` to `t`.



The word ‘Mail’ appears after the load level if there is mail for you that you have not read yet.  On graphical displays, you can use an icon instead of ‘Mail’ by customizing `display-time-use-mail-icon`; this may save some space on the mode line.  You can customize `display-time-mail-face` to make the mail indicator prominent.  Use `display-time-mail-file` to specify the mail file to check, or set `display-time-mail-directory` to specify the directory to check for incoming mail (any nonempty regular file in the directory is considered to be newly arrived mail).



When running Emacs on a laptop computer, you can display the battery charge on the mode-line, by using the command `display-battery-mode` or customizing the variable `display-battery-mode`.  The variable `battery-mode-line-format` determines the way the battery charge is displayed; the exact mode-line message depends on the operating system, and it usually shows the current battery charge as a percentage of the total charge.  The functions in `battery-update-functions` are run after updating the mode line, and can be used to trigger actions based on the battery status.



On graphical displays, the mode line is drawn as a 3D box.  If you don’t like this effect, you can disable it by customizing the `mode-line` face and setting its `box` attribute to `nil`.  See [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html).



By default, the mode line of nonselected windows is displayed in a different face, called `mode-line-inactive`.  Only the selected window is displayed in the `mode-line` face.  This helps show which window is selected.  When the minibuffer is selected, since it has no mode line, the window from which you activated the minibuffer has its mode line displayed using `mode-line`; as a result, ordinary entry to the minibuffer does not change any mode lines.



You can disable use of `mode-line-inactive` by setting variable `mode-line-in-non-selected-windows` to `nil`; then all mode lines are displayed in the `mode-line` face.



You can customize the mode line display for each of the end-of-line formats by setting each of the variables `eol-mnemonic-unix`, `eol-mnemonic-dos`, `eol-mnemonic-mac`, and `eol-mnemonic-undecided` to the strings you prefer.



### 16.20 How Text Is Displayed



Most characters are *printing characters*: when they appear in a buffer, they are displayed literally on the screen.  Printing characters include ASCII numbers, letters, and punctuation characters, as well as many non-ASCII characters.



The ASCII character set contains non-printing *control characters*.  Two of these are displayed specially: the newline character (Unicode code point U+000A) is displayed by starting a new line, while the tab character (U+0009) is displayed as a space that extends to the next tab stop column (normally every 8 columns).  The number of spaces per tab is controlled by the buffer-local variable `tab-width`, which must have an integer value between 1 and 1000, inclusive.  Note that the way the tab character in the buffer is displayed has nothing to do with the definition of TAB as a command.

Other ASCII control characters, whose codes are below U+0020 (octal 40, decimal 32), are displayed as a caret (‘^’) followed by the non-control version of the character, with the `escape-glyph` face.  For instance, the ‘control-A’ character, U+0001, is displayed as ‘^A’.



The raw bytes with codes U+0080 (octal 200) through U+009F (octal 237) are displayed as *octal escape sequences*, with the `escape-glyph` face.  For instance, character code U+0098 (octal 230) is displayed as ‘\230’. If you change the buffer-local variable `ctl-arrow` to `nil`, the ASCII control characters are also displayed as octal escape sequences instead of caret escape sequences.  (You can also request that raw bytes be shown in hex, see [display-raw-bytes-as-hex](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display-Custom.html).)



Some non-ASCII characters have the same appearance as an ASCII space or hyphen (minus) character.  Such characters can cause problems if they are entered into a buffer without your realization, e.g., by yanking; for instance, source code compilers typically do not treat non-ASCII spaces as whitespace characters.  To deal with this problem, Emacs displays such characters specially: it displays U+00A0 NO-BREAK SPACE and other characters from the Unicode horizontal space class with the `nobreak-space` face, and it displays U+00AD SOFT HYPHEN, U+2010 HYPHEN, and U+2011 NON-BREAKING HYPHEN with the `nobreak-hyphen` face.  To disable this, change the variable `nobreak-char-display` to `nil`.  If you give this variable a non-`nil` and non-`t` value, Emacs instead displays such characters as a highlighted backslash followed by a space or hyphen.

You can customize the way any particular character code is displayed by means of a display table.  See [Display Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Display-Tables.html#Display-Tables) in The Emacs Lisp Reference Manual.



On graphical displays, some characters may have no glyphs in any of the fonts available to Emacs.  These *glyphless characters* are normally displayed as boxes containing the hexadecimal character code. Similarly, on text terminals, characters that cannot be displayed using the terminal encoding (see [Coding Systems for Terminal I/O](https://www.gnu.org/software/emacs/manual/html_node/emacs/Terminal-Coding.html)) are normally displayed as question signs.  You can control the display method by customizing the variable `glyphless-char-display-control`.  You can also customize the `glyphless-char` face to make these characters more prominent on display.  See [Glyphless Character Display](https://www.gnu.org/software/emacs/manual/html_node/elisp/Glyphless-Chars.html#Glyphless-Chars) in The Emacs Lisp Reference Manual, for details.



The `glyphless-display-mode` minor mode can be used to toggle the display of glyphless characters in the current buffer.  The glyphless characters will be displayed as boxes with acronyms of their names inside.



Emacs tries to determine if the curved quotes `‘` and `’` can be displayed on the current display.  By default, if this seems to be so, then Emacs will translate the ASCII quotes (‘`’ and ‘'’), when they appear in messages and help texts, to these curved quotes.  You can influence or inhibit this translation by customizing the user option `text-quoting-style` (see [Keys in Documentation](https://www.gnu.org/software/emacs/manual/html_node/elisp/Keys-in-Documentation.html#Keys-in-Documentation) in The Emacs Lisp Reference Manual).

If the curved quotes `‘`, `’`, `“`, and `”` are known to look just like ASCII characters, they are shown with the `homoglyph` face.  Curved quotes that are known not to be displayable are shown as their ASCII approximations ‘`’, ‘'’, and ‘"’ with the `homoglyph` face.



### 16.21 Displaying the Cursor



On a text terminal, the cursor’s appearance is controlled by the terminal, largely out of the control of Emacs.  Some terminals offer two different cursors: a visible static cursor, and a very visible blinking cursor.  By default, Emacs uses the very visible cursor, and switches to it when you start or resume Emacs.  If the variable `visible-cursor` is `nil` when Emacs starts or resumes, it uses the normal cursor.



On a graphical display, many more properties of the text cursor can be altered.  To customize its color, change the `:background` attribute of the face named `cursor` (see [Customizing Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Face-Customization.html)).  (The other attributes of this face have no effect; the text shown under the cursor is drawn using the frame’s background color.)  To change its shape, customize the buffer-local variable `cursor-type`; possible values are `box` (the default), `(box . size)` (box cursor becoming a hollow box under masked images larger than size pixels in either dimension), `hollow` (a hollow box), `bar` (a vertical bar), `(bar . n)` (a vertical bar n pixels wide), `hbar` (a horizontal bar), `(hbar . n)` (a horizontal bar n pixels tall), or `nil` (no cursor at all).



By default, the cursor stops blinking after 10 blinks, if Emacs does not get any input during that time; any input event restarts the count.  You can customize the variable `blink-cursor-blinks` to control that: its value says how many times to blink without input before stopping.  Setting that variable to a zero or negative value will make the cursor blink forever.  To disable cursor blinking altogether, change the variable `blink-cursor-mode` to `nil` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)), or add the line

```
  (blink-cursor-mode 0)
```

to your init file.  Alternatively, you can change how the cursor looks when it blinks off by customizing the list variable `blink-cursor-alist`.  Each element in the list should have the form `(on-type . off-type)`; this means that if the cursor is displayed as on-type when it blinks on (where on-type is one of the cursor types described above), then it is displayed as off-type when it blinks off.



Some characters, such as tab characters, are extra wide.  When the cursor is positioned over such a character, it is normally drawn with the default character width.  You can make the cursor stretch to cover wide characters, by changing the variable `x-stretch-cursor` to a non-`nil` value.



The cursor normally appears in non-selected windows as a non-blinking hollow box.  (For a bar cursor, it instead appears as a thinner bar.)  To turn off cursors in non-selected windows, change the variable `cursor-in-non-selected-windows` to `nil`.



To make the cursor even more visible, you can use HL Line mode, a minor mode that highlights the line containing point.  Use M-x hl-line-mode to enable or disable it in the current buffer.  M-x global-hl-line-mode enables or disables the same mode globally.



### 16.22 Line Truncation



As an alternative to continuation (see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html)), Emacs can display long lines by *truncation*.  This means that all the characters that do not fit in the width of the screen or window do not appear at all.  On graphical displays, a small straight arrow in the fringe indicates truncation at either end of the line.  On text terminals, this is indicated with ‘$’ signs in the rightmost and/or leftmost columns.



Horizontal scrolling automatically causes line truncation (see [Horizontal Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Horizontal-Scrolling.html)).  You can explicitly enable line truncation for a particular buffer with the command C-x x t (`toggle-truncate-lines`).  This works by locally changing the variable `truncate-lines`.  If that variable is non-`nil`, long lines are truncated; if it is `nil`, they are continued onto multiple screen lines.  Setting the variable `truncate-lines` in any way makes it local to the current buffer; until that time, the default value, which is normally `nil`, is in effect.

Since line truncation and word wrap (described in the next section) are contradictory, `toggle-truncate-lines` disables word wrap when it turns on line truncation.

If a split window becomes too narrow, Emacs may automatically enable line truncation.  See [Splitting Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Split-Window.html), for the variable `truncate-partial-width-windows` which controls this.



### 16.23 Visual Line Mode



Another alternative to ordinary line continuation (see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html)) is to use *word wrap*.  Here, each long logical line is divided into two or more screen lines, or “visual lines”, like in ordinary line continuation.  However, Emacs attempts to wrap the line at word boundaries near the right window edge.  (If the line’s direction is right-to-left, it is wrapped at the left window edge instead.)  This makes the text easier to read, as wrapping does not occur in the middle of words.



Word wrap is enabled by Visual Line mode, an optional minor mode. To turn on Visual Line mode in the current buffer, type M-x visual-line-mode; repeating this command turns it off.  You can also turn on Visual Line mode using the menu bar: in the Options menu, select the ‘Line Wrapping in this Buffer’ submenu, followed by the ‘Word Wrap (Visual Line mode)’ menu item.  While Visual Line mode is enabled, the mode line shows the string ‘wrap’ in the mode display.  The command M-x global-visual-line-mode toggles Visual Line mode in all buffers.

Since word wrap and line truncation (described in the previous section) are contradictory, turning on `visual-line-mode` disables line truncation.



In Visual Line mode, some editing commands work on screen lines instead of logical lines: C-a (`beginning-of-visual-line`) moves to the beginning of the screen line, C-e (`end-of-visual-line`) moves to the end of the screen line, and C-k (`kill-visual-line`) kills text to the end of the screen line.

To move by logical lines, use the commands M-x next-logical-line and M-x previous-logical-line.  These move point to the next logical line and the previous logical line respectively, regardless of whether Visual Line mode is enabled.  If you use these commands frequently, it may be convenient to assign key bindings to them.  See [Rebinding Keys in Your Init File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-Rebinding.html).

By default, word-wrapped lines do not display fringe indicators. Visual Line mode is often used to edit files that contain many long logical lines, so having a fringe indicator for each wrapped line would be visually distracting.  You can change this by customizing the variable `visual-line-fringe-indicators`.



By default, Emacs only breaks lines after whitespace characters like SPC and TAB, but does not break after whitespace characters like EN QUAD.  Emacs provides a minor mode called `word-wrap-whitespace-mode` that switches on word wrapping in the current mode, and sets up which characters to wrap lines on based on the `word-wrap-whitespace-characters` user option.  There’s also a globalized version of that mode called `global-word-wrap-whitespace-mode`.



Only breaking after whitespace character produces incorrect results when CJK and Latin text are mixed together (because CJK characters don’t use whitespace to separate words).  You can customize the option `word-wrap-by-category` to allow Emacs to break lines after any character with ‘|’ category (see [Categories](https://www.gnu.org/software/emacs/manual/html_node/elisp/Categories.html#Categories) in the Emacs Lisp Reference Manual), which provides better support for CJK characters.  Also, if this variable is set using Customize, Emacs automatically loads kinsoku.el. When kinsoku.el is loaded, Emacs respects kinsoku rules when breaking lines.  That means characters with the ‘>’ category don’t appear at the beginning of a line (e.g., U+FF0C FULLWIDTH COMMA), and characters with the ‘<’ category don’t appear at the end of a line (e.g., U+300A LEFT DOUBLE ANGLE BRACKET).  You can view the category set of a character using the commands `char-category-set` and `category-set-mnemonics`, or by typing C-u C-x = with point on the character and looking at the “category” section in the report.  You can add categories to a character using the command `modify-category-entry`.



### 16.24 Customization of Display

This section describes variables that control miscellaneous aspects of the appearance of the Emacs screen.  Beginning users can skip it.



If you want to have Emacs display line numbers for every line in the buffer, customize the buffer-local variable `display-line-numbers`; it is `nil` by default.  This variable can have several different values to support various modes of line-number display:

- `t`

  Display (an absolute) line number before each non-continuation screen line that displays buffer text.  If the line is a continuation line, or if the entire screen line displays a display or an overlay string, that line will not be numbered.

- `relative`

  Display relative line numbers before non-continuation lines which show buffer text.  The line numbers are relative to the line showing point, so the numbers grow both up and down as lines become farther from the current line.

- `visual`

  This value causes Emacs to count lines visually: only lines actually shown on the display will be counted (disregarding any lines in invisible parts of text), and lines which wrap to consume more than one screen line will be numbered that many times.  The displayed numbers are relative, as with `relative` value above.  This is handy in modes that fold text, such as Outline mode (see [Outline Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Mode.html)), and when you need to move by exact number of screen lines.

- anything else

  Any other non-`nil` value is treated as `t`.



The command M-x display-line-numbers-mode provides a convenient way to turn on display of line numbers.  This mode has a globalized variant, `global-display-line-numbers-mode`.  The user option `display-line-numbers-type` controls which sub-mode of line-number display, described above, these modes will activate.

Note that line numbers are not displayed in the minibuffer and in the tooltips, even if you turn on `display-line-numbers-mode` globally.



When Emacs displays relative line numbers, you can control the number displayed before the current line, the line showing point.  By default, Emacs displays the absolute number of the current line there, even though all the other line numbers are relative.  If you customize the variable `display-line-numbers-current-absolute` to a `nil` value, the number displayed for the current line will be zero.  This is handy if you don’t care about the number of the current line, and want to leave more horizontal space for text in large buffers.



In a narrowed buffer (see [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html)) lines are normally numbered starting at the beginning of the narrowing.  However, if you customize the variable `display-line-numbers-widen` to a non-`nil` value, line numbers will disregard any narrowing and will start at the first character of the buffer.



If the value of `display-line-numbers-offset` is non-zero, it is added to each absolute line number, and lines are counted from the beginning of the buffer, as if `display-line-numbers-widen` were non-`nil`.  It has no effect when set to zero, or when line numbers are not absolute.



In selective display mode (see [Selective Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Selective-Display.html)), and other modes that hide many lines from display (such as Outline and Org modes), you may wish to customize the variables `display-line-numbers-width-start` and `display-line-numbers-grow-only`, or set `display-line-numbers-width` to a large enough value, to avoid occasional miscalculations of space reserved for the line numbers.



The line numbers are displayed in a special face `line-number`. The current line number is displayed in a different face, `line-number-current-line`, so you can make the current line’s number have a distinct appearance, which will help locating the line showing point.  Additional faces `line-number-major-tick` and `line-number-minor-tick` can be used to highlight the line numbers of lines which are a multiple of certain numbers.  Customize `display-line-numbers-major-tick` and `display-line-numbers-minor-tick` respectively to set those numbers.



If the variable `visible-bell` is non-`nil`, Emacs attempts to make the whole screen blink when it would normally make an audible bell sound.  This variable has no effect if your terminal does not have a way to make the screen blink.



The variable `echo-keystrokes` controls the echoing of multi-character keys; its value is the number of seconds of pause required to cause echoing to start, or zero, meaning don’t echo at all.  The value takes effect when there is something to echo.  See [The Echo Area](https://www.gnu.org/software/emacs/manual/html_node/emacs/Echo-Area.html).



If the variable `echo-keystrokes-help` is non-`nil` (the default), the multi-character key sequence echo shown according to `echo-keystrokes` will include a short help text about keys which will invoke `describe-prefix-bindings` (see [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html)) to show the list of commands for the prefix you already typed.  For a related help facility, see [which-key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html#which_002dkey).



On graphical displays, Emacs displays the mouse pointer as an hourglass if Emacs is busy.  To disable this feature, set the variable `display-hourglass` to `nil`.  The variable `hourglass-delay` determines the number of seconds of busy time before the hourglass is shown; the default is 1.



If the mouse pointer lies inside an Emacs frame, Emacs makes it invisible each time you type a character to insert text, to prevent it from obscuring the text.  (To be precise, the hiding occurs when you type a self-inserting character.  See [Inserting Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Inserting-Text.html).)  Moving the mouse pointer makes it visible again.  To disable this feature, set the variable `make-pointer-invisible` to `nil`.



On graphical displays, the variable `underline-minimum-offset` determines the minimum distance between the baseline and underline, in pixels, for underlined text.  By default, the value is 1; increasing it may improve the legibility of underlined text for certain fonts. (However, Emacs will never draw the underline below the current line area.)  The variable `x-underline-at-descent-line` determines how to draw underlined text.  The default is `nil`, which means to draw it at the baseline level of the font; if you change it to `t`, Emacs draws the underline at the same height as the font’s descent line.  (If non-default line spacing was specified for the underlined text, see [Line Height](https://www.gnu.org/software/emacs/manual/html_node/elisp/Line-Height.html#Line-Height) in The Emacs Lisp Reference Manual, Emacs draws the underline below the additional spacing.)



The variable `overline-margin` specifies the vertical position of an overline above the text, including the height of the overline itself, in pixels; the default is 2.



On some text terminals, bold face and inverse video together result in text that is hard to read.  Call the function `tty-suppress-bold-inverse-default-colors` with a non-`nil` argument to suppress the effect of bold-face in this case.



Raw bytes are displayed in octal format by default, for example a byte with a decimal value of 128 is displayed as `\200`.  To change display to the hexadecimal format of `\x80`, set the variable `display-raw-bytes-as-hex` to `t`. Care may be needed when interpreting a raw byte when copying text from a terminal containing an Emacs session, or when a terminal’s `escape-glyph` face looks like the default face.  For example, by default Emacs displays the four characters ‘\’, ‘2’, ‘0’, ‘0’ with the same characters it displays a byte with decimal value 128.  The problem can be worse with hex displays, where the raw byte 128 followed by the character ‘7’ is displayed as `\x807`, which Emacs Lisp reads as the single character U+0807 SAMARITAN LETTER IT; this confusion does not occur with the corresponding octal display `\2007` because octal escapes contain at most three digits.



## 17 Searching and Replacement



Like other editors, Emacs has commands to search for occurrences of a string.  Emacs also has commands to replace occurrences of a string with a different string.  There are also commands that do the same thing, but search for patterns instead of fixed strings.

You can also search multiple files under the control of `xref` (see [Searching and Replacing with Identifiers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Identifier-Search.html)) or through the Dired A command (see [Operating on Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Operating-on-Files.html)), or ask the `grep` program to do it (see [Searching with Grep under Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Grep-Searching.html)).



### 17.1 Incremental Search



The principal search command in Emacs is *incremental*: it begins searching as soon as you type the first character of the search string.  As you type in the search string, Emacs shows you where the string (as you have typed it so far) would be found.  When you have typed enough characters to identify the place you want, you can stop. Depending on what you plan to do next, you may or may not need to terminate the search explicitly with RET.

- C-s

  Incremental search forward (`isearch-forward`).

- C-r

  Incremental search backward (`isearch-backward`).

You can also invoke incremental search from the menu bar’s ‘Edit->Search’ menu.



#### 17.1.1 Basics of Incremental Search

- C-s

  Begin incremental search (`isearch-forward`).

- C-r

  Begin reverse incremental search (`isearch-backward`).



C-s (`isearch-forward`) starts a forward incremental search.  It reads characters from the keyboard, and moves point just past the end of the next occurrence of those characters in the buffer.

For instance, if you type C-s and then F, that puts the cursor after the first ‘F’ that occurs in the buffer after the starting point.  If you then type O, the cursor moves to just after the first ‘FO’; the ‘F’ in that ‘FO’ might not be the first ‘F’ previously found.  After another O, the cursor moves to just after the first ‘FOO’.



At each step, Emacs highlights the *current match*—the buffer text that matches the search string—using the `isearch` face (see [Text Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Faces.html)).  See [Tailoring Search to Your Needs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html), for various options that customize this highlighting.  The current search string is also displayed in the echo area.



If you make a mistake typing the search string, type DEL (`isearch-delete-char`).  Each DEL cancels the last input item entered during the search.  Emacs records a new *input item* whenever you type a command that changes the search string, the position of point, the success or failure of the search, the direction of the search, the position of the other end of the current search result, or the “wrappedness” of the search.  See [Errors in Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Error-in-Isearch.html), for more about dealing with unsuccessful search.



When you are satisfied with the place you have reached, type RET (`isearch-exit`).  This stops searching, leaving the cursor where the search brought it.  Also, any command not specially meaningful in searches stops the searching and is then executed. Thus, typing C-a exits the search and then moves to the beginning of the line; typing one of the arrow keys exits the search and performs the respective movement command; etc.  RET is necessary only if the next command you want to type is a printing character, DEL, RET, or another character that is special within searches (C-q, C-w, C-r, C-s, C-y, M-y, M-r, M-c, M-e, and some others described below).  You can fine-tune the commands that exit the search; see [Not Exiting Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Not-Exiting-Isearch.html).

As a special exception, entering RET when the search string is empty launches nonincremental search (see [Nonincremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Nonincremental-Search.html)). (This can be customized; see [Tailoring Search to Your Needs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html).)



To abandon the search and return to the place where you started, type ESC ESC ESC (`isearch-cancel`) or C-g C-g (`isearch-abort`).

When you exit the incremental search, it adds the original value of point to the mark ring, without activating the mark; you can thus use C-u C-SPC or C-x C-x to return to where you were before beginning the search.  See [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html).  (Emacs only does this if the mark was not already active; if the mark was active when you started the search, both C-u C-SPC and C-x C-x will go to the mark.)



To search backwards, use C-r (`isearch-backward`) instead of C-s to start the search.  A backward search finds matches that end before the starting point, just as a forward search finds matches that begin after it.



#### 17.1.2 Repeating Incremental Search



Suppose you search forward for ‘FOO’ and find a match, but not the one you expected to find: the ‘FOO’ you were aiming for occurs later in the buffer.  In this event, type another C-s (`isearch-repeat-forward`) to move to the next occurrence of the search string, or C-r (`isearch-repeat-backward`) to move to the previous occurrence.  You can repeat these commands any number of times.  Alternatively, you can supply a numeric prefix argument of n to C-s and C-r to find the nth next or previous occurrence.  If you overshoot, you can cancel some C-s commands with DEL.  Similarly, each C-r (`isearch-repeat-backward`) in a backward incremental search repeats the backward search.



If you pause for a little while during incremental search, Emacs highlights all the other possible matches for the search string that are present on the screen.  This helps you anticipate where you can get to by typing C-s or C-r to repeat the search.  The other matches are highlighted differently from the current match, using the customizable face `lazy-highlight` (see [Text Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Faces.html)).  If you don’t like this feature, you can disable it by setting `isearch-lazy-highlight` to `nil`.  For other customizations related to highlighting matches, see [Tailoring Search to Your Needs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html).

After exiting a search, you can search for the same string again by typing just C-s C-s.  The first C-s is the key that invokes incremental search, and the second C-s means to search again for the last search string.  Similarly, C-r C-r searches backward for the last search string.  In determining the last search string, it doesn’t matter whether that string was searched for with C-s or C-r.

If you are searching forward but you realize you were looking for something before the starting point, type C-r to switch to a backward search, leaving the search string unchanged.  Similarly, C-s in a backward search switches to a forward search.



When you change the direction of a search, the first command you type will, by default, remain on the same match, and the cursor will move to the other end of the match.  To move to another match immediately, customize the variable `isearch-repeat-on-direction-change` to `t`.



If a search is failing and you ask to repeat it by typing another C-s, it starts again from the beginning of the buffer. Repeating a failing reverse search with C-r starts again from the end.  This is called *wrapping around*, and ‘Wrapped’ appears in the search prompt once this has happened.  If you keep on going past the original starting point of the search, it changes to ‘Overwrapped’, which means that you are revisiting matches that you have already seen.



You can control what happens when there are no more matches by customizing the `isearch-wrap-pause` user option.  If it is `t` (the default), signal an error.  (Repeating the search will wrap around.)  If `no`, issue a `ding` and wrap immediately after reaching the last match.  If `no-ding`, wrap immediately, but don’t `ding`.  With the values `no` and `no-ding` the search will try to wrap around also on typing a character. Finally, if `nil`, never wrap, but just stop at the last match.



To reuse earlier search strings, use the *search ring*.  The commands M-p (`isearch-ring-retreat`) and M-n (`isearch-ring-advance`) move through the ring to pick a search string to reuse.  These commands leave the selected search ring element in the minibuffer, where you can edit it.  Type C-s/C-r or RET to accept the string and start searching for it.  The number of most recently used search strings saved in the search ring is specified by the variable `search-ring-max`, 16 by default.



To edit the current search string in the minibuffer without replacing it with items from the search ring, type M-e (`isearch-edit-string`) or click mouse-1 in the minibuffer. Type RET, C-s or C-r to finish editing the string and search for it.  Type C-f or RIGHT to add to the search string characters following point from the buffer from which you started the search.



#### 17.1.3 Isearch Yanking

In many cases, you will want to use text at or near point as your search string.  The commands described in this subsection let you do that conveniently.



C-w (`isearch-yank-word-or-char`) appends the next character or word at point to the search string.  This is an easy way to search for another occurrence of the text at point.  (The decision of whether to copy a character or a word is heuristic.)  With a prefix numeric argument of n, append the next n characters or words.



C-M-w (`isearch-yank-symbol-or-char`) appends the next character or symbol at point to the search string.  This is an easy way to search for another occurrence of the symbol at point.  (The decision of whether to copy a character or a symbol is heuristic.)  With a prefix numeric argument of n, append the next n characters or symbols.



M-s C-e (`isearch-yank-line`) appends the rest of the current line to the search string.  If point is already at the end of a line, it appends the next line.  With a prefix argument n, it appends the next n lines.



Similarly, C-M-z (`isearch-yank-until-char`) appends to the search string everything from point until the next occurrence of a specified character (not including that character).  This is especially useful for keyboard macros, for example in programming languages or markup languages in which that character marks a token boundary.  With a prefix numeric argument of n, the command appends everything from point to the nth occurrence of the specified character.



Within incremental search, C-y (`isearch-yank-kill`) appends the current kill to the search string.  M-y (`isearch-yank-pop`), if called after C-y during incremental search, replaces that appended text with an earlier kill, similar to the usual M-y (`yank-pop`) command.  Clicking mouse-2 in the echo area appends the current X selection (see [Cut and Paste with Other Window Applications](https://www.gnu.org/software/emacs/manual/html_node/emacs/Primary-Selection.html)) to the search string (`isearch-yank-x-selection`).



C-M-d (`isearch-del-char`) deletes the last character from the search string, and C-M-y (`isearch-yank-char`) appends the character after point to the search string.  An alternative method to add the character after point is to enter the minibuffer with M-e (see [Repeating Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Repeat-Isearch.html)) and type C-f or RIGHT at the end of the search string in the minibuffer.  Each C-f or RIGHT you type adds another character following point to the search string.

Normally, when the search is case-insensitive, text yanked into the search string is converted to lower case, so that the search remains case-insensitive (see [case folding](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)).  However, if the value of the variable `search-upper-case` (see [search-upper-case](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) is other than `not-yanks`, that disables this down-casing.



To begin a new incremental search with the text near point yanked into the initial search string, type M-s M-., which runs the command `isearch-forward-thing-at-point`.  If the region was active, then it yanks the text from the region into the search string. Otherwise, it tries to yank a URL, a symbol or an expression found near point.  What to yank is defined by the user option `isearch-forward-thing-at-point`.



#### 17.1.4 Errors in Incremental Search



If your string is not found at all, the echo area says ‘Failing I-Search’, and the cursor moves past the place where Emacs found as much of your string as it could.  Thus, if you search for ‘FOOT’, and there is no ‘FOOT’, you might see the cursor after the ‘FOO’ in ‘FOOL’.  In the echo area, the part of the search string that failed to match is highlighted using the face `isearch-fail`.

At this point, there are several things you can do.  If your string was mistyped, use DEL to cancel a previous input item (see [Basics of Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Basic-Isearch.html)), C-M-d to erase one character at a time, or M-e to edit it.  If you like the place you have found, you can type RET to remain there.  Or you can type C-g, which removes from the search string the characters that could not be found (the ‘T’ in ‘FOOT’), leaving those that were found (the ‘FOO’ in ‘FOOT’).  A second C-g at that point cancels the search entirely, returning point to where it was when the search started.



The quit command, C-g, does special things during searches; just what it does depends on the status of the search.  If the search has found what you specified and is waiting for input, C-g cancels the entire search, moving the cursor back to where you started the search.  If C-g is typed when there are characters in the search string that have not been found—because Emacs is still searching for them, or because it has failed to find them—then the search string characters which have not been found are discarded from the search string.  With them gone, the search is now successful and waiting for more input, so a second C-g will cancel the entire search.



#### 17.1.5 Special Input for Incremental Search

In addition to characters described in the previous subsections, some of the other characters you type during incremental search have special effects.  They are described here.

To toggle lax space matching (see [lax space matching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)), type M-s SPC.

To toggle case sensitivity of the search, type M-c or M-s c.  See [case folding](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html).  If the search string includes upper-case letters, the search is case-sensitive by default.

To toggle whether or not the search will consider similar and equivalent characters as a match, type M-s '.  See [character folding](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html).  If the search string includes accented characters, that disables character folding during that search.



To toggle whether or not the search will find text made invisible by overlays, type M-s i (`isearch-toggle-invisible`). See [Outline Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Visibility.html#Outline-Search).  To make all incremental searches find matches inside invisible text, whether due to text properties or overlay properties, customize `search-invisible` to the value `t`.



To toggle between non-regexp and regexp incremental search, type M-r or M-s r (`isearch-toggle-regexp`). See [Regular Expression Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Search.html).

To toggle symbol mode, type M-s _.  See [Symbol Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Symbol-Search.html).

To search for a newline character, type C-j as part of the search string.

To search for non-ASCII characters, use one of the following methods during incremental search:

- Type C-q (`isearch-quote-char`), followed by a non-graphic character or a sequence of octal digits.  This adds a character to the search string, similar to inserting into a buffer using C-q (see [Inserting Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Inserting-Text.html)).  For example, C-q C-s during incremental search adds the ‘control-S’ character to the search string.

- 

  

  

  

  Use an input method (see 

  Input Methods

  ).  If an input method is enabled in the current buffer when you start the search, the same method will be active in the minibuffer when you type the search string.  While typing the search string, you can toggle the input method with 

  C-\

   (

  ```
  isearch-toggle-input-method
  ```

  ).  You can also turn on a non-default input method with 

  C-^

  (

  ```
  isearch-toggle-specified-input-method
  ```

  ), which prompts for the name of the input method.  When an input method is active during incremental search, the search prompt includes the input method mnemonic, like this:

  ```
  I-search [im]:
  ```

  where im is the mnemonic of the active input method.  Any input method you enable during incremental search remains enabled in the current buffer afterwards.  Finally, you can temporarily enable a transient input method (see [transient input method](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Input-Method.html#transient-input-method)) with C-x \ (`isearch-transient-input-method`) to insert a single character to the search string using an input method, and automatically disable the input method afterwards.

- Type C-x 8 RET (`isearch-char-by-name`), followed by a Unicode name or code-point in hex.  This adds the specified character into the search string, similar to the usual `insert-char` command (see [Inserting Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Inserting-Text.html)).



You can also include Emoji sequences in the search string.  Type C-x 8 e RET (`isearch-emoji-by-name`), followed by the Unicode name of an Emoji (for example, smiling face or heart with arrow).  This adds the specified Emoji to the search string.  If you don’t know the name of the Emoji you want to search for, you can use C-x 8 e l (`emoji-list`) and C-x 8 e d (`emoji-describe`) (see [Input Methods](https://www.gnu.org/software/emacs/manual/html_node/emacs/Input-Methods.html)).



Typing M-s o in incremental search invokes `isearch-occur`, which runs `occur` with the current search string.  See [occur](https://www.gnu.org/software/emacs/manual/html_node/emacs/Other-Repeating-Search.html).



Typing M-% (`isearch-query-replace`) in incremental search invokes `query-replace` or `query-replace-regexp` (depending on search mode) with the current search string used as the string to replace.  A negative prefix argument means to replace backward.  See [Query Replace](https://www.gnu.org/software/emacs/manual/html_node/emacs/Query-Replace.html).  Typing C-M-% (`isearch-query-replace-regexp`) invokes `query-replace-regexp` with the current search string used as the regexp to replace.



Typing M-TAB in incremental search invokes `isearch-complete`, which attempts to complete the search string using the search ring (the previous search strings you used) as a list of completion alternatives.  See [Completion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion.html).  In many operating systems, the M-TAB key sequence is captured by the window manager; you then need to rebind `isearch-complete` to another key sequence if you want to use it (see [Changing Key Bindings Interactively](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rebinding.html)).



You can exit the search while leaving the matches highlighted by typing M-s h r (`isearch-highlight-regexp`).  This runs `highlight-regexp` (see [Interactive Highlighting](https://www.gnu.org/software/emacs/manual/html_node/emacs/Highlight-Interactively.html)), passing it the regexp derived from the search string and prompting you for the face to use for highlighting.  To highlight *whole lines* containing matches (rather than *just* the matches), type M-s h l (`isearch-highlight-lines-matching-regexp`).  In either case, to remove the highlighting, type M-s h u (`unhighlight-regexp`).



When incremental search is active, you can type C-h C-h (`isearch-help-map`) to access interactive help options, including a list of special key bindings.  These key bindings are part of the keymap `isearch-mode-map` (see [Keymaps](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keymaps.html)).



When incremental search is active, typing M-s M-> will go to the last occurrence of the search string, and M-s M-< will go to the first occurrence.  With a prefix numeric argument of n, these commands will go to the nth occurrence of the search string counting from the beginning or end of the buffer, respectively.



#### 17.1.6 Not Exiting Incremental Search

This subsection describes how to control whether typing a command not specifically meaningful in searches exits the search before executing the command.  It also describes three categories of commands which you can type without exiting the current incremental search, even though they are not themselves part of incremental search.



Normally, typing a command that is not bound by the incremental search exits the search before executing the command.  Thus, the command operates on the buffer from which you invoked the search. However, if you customize the variable `search-exit-option` to `append`, the characters which you type that are not interpreted by the incremental search are simply appended to the search string.  This is so you could include in the search string control characters, such as C-a, that would normally exit the search and invoke the command bound to them on the buffer.

- Prefix Arguments[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Not-Exiting-Isearch.html#index-prefix-argument-commands_002c-during-incremental-search)

  In incremental search, when you type a command that specifies a prefix argument (see [Numeric Arguments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Arguments.html)), by default it will apply either to the next action in the search or to the command that exits the search.  In other words, entering a prefix argument will not by itself terminate the search. In previous versions of Emacs, entering a prefix argument always terminated the search.  You can revert to this behavior by setting the variable `isearch-allow-prefix` to `nil`. When `isearch-allow-scroll` is non-`nil` (see below), prefix arguments always have the default behavior described above, i.e., they don’t terminate the search, even if `isearch-allow-prefix` is `nil`.

- Scrolling Commands[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Not-Exiting-Isearch.html#index-scrolling-commands_002c-during-incremental-search)

  Normally, scrolling commands exit incremental search.  But if you change the variable `isearch-allow-scroll` to a non-`nil` value, that enables the use of the scroll-bar, as well as keyboard scrolling commands like C-v, M-v, and C-l (see [Scrolling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scrolling.html)), which have a non-`nil` `scroll-command` property, without exiting the search.  This applies only to calling these commands via their bound key sequences—typing M-x will still exit the search.  You can give prefix arguments to these commands in the usual way.  This feature normally won’t let you scroll the current match out of visibility; but if you customize `isearch-allow-scroll` to the special value `unlimited`, that restriction is lifted.  The `isearch-allow-scroll` feature also affects some other commands, such as C-x 2 (`split-window-below`) and C-x ^ (`enlarge-window`), which don’t exactly scroll but do affect where the text appears on the screen.  In fact, it affects any command that has a non-`nil` `isearch-scroll` property. So you can control which commands are affected by changing these properties. For example, to make C-h l usable within an incremental search in all future Emacs sessions, use C-h c to find what command it runs (see [Documentation for a Key](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Help.html)), which is `view-lossage`.  Then you can put the following line in your init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):  `(put 'view-lossage 'isearch-scroll t) ` This feature can be applied to any command that doesn’t permanently change point, the buffer contents, the match data, the current buffer, or the selected window and frame.  The command must not itself attempt an incremental search.  This feature is disabled if `isearch-allow-scroll` is `nil` (which it is by default).  Likewise, if you change the variable `isearch-allow-motion` to a non-`nil` value, this enables the use of the keyboard motion commands M-<, M->, C-v and M-v, to move respectively to the first occurrence of the current search string in the buffer, the last one, the first one after the current window, and the last one before the current window.  The search direction does not change when these motion commands are used, unless you change the variable `isearch-motion-changes-direction` to a non-`nil` value, in which case the search direction is forward after M-< and C-v, and backward after M-> and M-v.

- Motion Commands[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Not-Exiting-Isearch.html#index-motion-commands_002c-during-incremental-search)

  When `isearch-yank-on-move` is customized to `shift`, you can extend the search string by holding down the shift key while typing cursor motion commands.  It will yank text that ends at the new position after moving point in the current buffer.  When `isearch-yank-on-move` is `t`, you can extend the search string without using the shift key for cursor motion commands, but it applies only for certain motion command that have the `isearch-move` property on their symbols.



#### 17.1.7 Searching the Minibuffer



If you start an incremental search while the minibuffer is active, Emacs searches the contents of the minibuffer.  Unlike searching an ordinary buffer, the search string is not shown in the echo area, because that is used to display the minibuffer.

If an incremental search fails in the minibuffer, it tries searching the minibuffer history.  See [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html).  You can visualize the minibuffer and its history as a series of pages, with the earliest history element on the first page and the current minibuffer on the last page.  A forward search, C-s, searches forward to later pages; a reverse search, C-r, searches backwards to earlier pages.  Like in ordinary buffer search, a failing search can wrap around, going from the last page to the first page or vice versa.

When the current match is on a history element, that history element is pulled into the minibuffer.  If you exit the incremental search normally (e.g., by typing RET), it remains in the minibuffer afterwards.  Canceling the search, with C-g, restores the contents of the minibuffer when you began the search.



### 17.2 Nonincremental Search



Emacs also has conventional nonincremental search commands, which require you to type the entire search string before searching begins.

- C-s RET string RET

  Search for string.

- C-r RET string RET

  Search backward for string.

To start a nonincremental search, first type C-s RET. This enters the minibuffer to read the search string; terminate the string with RET, and then the search takes place.  If the string is not found, the search command signals an error.

When you type C-s RET, the C-s invokes incremental search as usual.  That command is specially programmed to invoke the command for nonincremental search, if the string you specify is empty. (Such an empty argument would otherwise be useless.)  C-r RET does likewise, invoking the nonincremental backward-searching command.

Nonincremental search can also be invoked from the menu bar’s ‘Edit->Search’ menu.



You can also use two simpler commands, M-x search-forward and M-x search-backward.  These commands look for the literal strings you specify, and don’t support any of the lax-search features (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) except case folding.



### 17.3 Word Search



A *word search* finds a sequence of words without regard to the type of punctuation between them.  For instance, if you enter a search string that consists of two words separated by a single space, the search matches any sequence of those two words separated by one or more spaces, newlines, or other punctuation characters.  This is particularly useful for searching text documents, because you don’t have to worry whether the words you are looking for are separated by newlines or spaces.  Note that major modes for programming languages or other specialized modes can modify the definition of a word to suit their syntactic needs.

- M-s w

  If incremental search is active, toggle word search mode (`isearch-toggle-word`); otherwise, begin an incremental forward word search (`isearch-forward-word`).

- M-s w RET words RET

  Search for words, using a forward nonincremental word search.

- M-s w C-r RET words RET

  Search backward for words, using a nonincremental word search.

- M-s M-w

  Search the Web for the text in region.



To begin a forward incremental word search, type M-s w.  If incremental search is not already active, this runs the command `isearch-forward-word`.  If incremental search is already active (whether a forward or backward search), M-s w runs the command `isearch-toggle-word`, which switches to a word search while keeping the direction of the search and the current search string unchanged.  You can toggle word search back off by typing M-s w again.



To begin a nonincremental word search, type M-s w RET for a forward search, or M-s w C-r RET for a backward search. These run the commands `word-search-forward` and `word-search-backward` respectively.

Incremental and nonincremental word searches differ slightly in the way they find a match.  In a nonincremental word search, each word in the search string must exactly match a whole word.  In an incremental word search, the matching is more lax: while you are typing the search string, its first and last words need not match whole words.  This is so that the matching can proceed incrementally as you type.  This additional laxity does not apply to the lazy highlight (see [Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Incremental-Search.html)), which always matches whole words. While you are typing the search string, ‘Pending’ appears in the search prompt until you use a search repeating key like C-s.

The word search commands don’t perform character folding, and toggling lax whitespace matching (see [lax space matching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) has no effect on them.



To search the Web for the text in region, type M-s M-w.  This command performs an Internet search for the words in region using the search engine whose URL is specified by the variable `eww-search-prefix` (see [EWW](https://www.gnu.org/software/emacs/manual/html_node/eww/Basics.html#Basics) in The Emacs Web Wowser Manual).  If the region is not active, or doesn’t contain any words, this command prompts the user for a URL or keywords to search.



You can also search for definitions of a word by querying dictionary servers via the DICT protocol defined by RFC 2229.  Emacs includes a client for this protocol.  Type M-x dictionary-search RET to connect to a DICT server and ask it to provide the available definitions of a word.  This command prompts for the word to look up, using the word at point as the default, then asks the DICT server to provide the definitions of that word in one or more dictionaries.  By default, the command first tries to connect to the DICT server installed on the local host, and if that fails, it tries dict.org after asking for confirmation; customize the variable `dictionary-server` to specify, as a string, the URL of a single server to use (use ‘localhost’ if you want to query only the local server).  Normally, `dictionary-search` tells the server to look up the word in all the dictionaries available to the server, but if you invoke the command with a prefix argument, it will prompt for a single dictionary to use.  The list of dictionaries available to a server can be displayed by pressing the ‘Select dictionary’ button shown in the *Dictionary* buffer, described below.

The first time you use `dictionary-search`, it creates a new *Dictionary* buffer and turns on a special mode in it.  The buffer shows buttons for selecting a dictionary, searching a definition of another word, etc.  Subsequent `dictionary-search` commands reuse this buffer.  To create another such buffer (e.g., to look up other words, perhaps in another dictionary), type M-x dictionary RET.

If you turn on `dictionary-tooltip-mode` in a buffer, Emacs will look up the definitions of the word at mouse pointer and show those definitions in a tool tip.  This is handy when you are reading text with many words about whose meaning you are unsure.

For other options of dictionary-search, see the `dictionary` customization group (see [Customizing Specific Items](https://www.gnu.org/software/emacs/manual/html_node/emacs/Specific-Customization.html)).



### 17.4 Symbol Search



A *symbol search* is much like an ordinary search, except that the boundaries of the search must match the boundaries of a symbol. The meaning of *symbol* in this context depends on the major mode, and usually refers to a source code token, such as a Lisp symbol in Emacs Lisp mode.  For instance, if you perform an incremental symbol search for the Lisp symbol `forward-word`, it would not match `isearch-forward-word`.  This feature is thus mainly useful for searching source code.

- M-s _[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Symbol-Search.html#index-isearch_002dtoggle_002dsymbol)

  If incremental search is active, toggle symbol search mode (`isearch-toggle-symbol`); otherwise, begin an incremental forward symbol search (`isearch-forward-symbol`).

- M-s .

  Start a symbol incremental search forward with the symbol found near point added to the search string initially.

- M-s _ RET symbol RET

  Search forward for symbol, nonincrementally.

- M-s _ C-r RET symbol RET

  Search backward for symbol, nonincrementally.



To begin a forward incremental symbol search, type M-s _ (or M-s . if the symbol to search is near point).  If incremental search is not already active, M-s _ runs the command `isearch-forward-symbol` and M-s . runs the command `isearch-forward-symbol-at-point`.  With a numeric prefix argument of n, M-s . will search for the nthe next occurrence of the symbol at point; negative values of n search backwards.  If incremental search is already active, M-s _ switches to a symbol search, preserving the direction of the search and the current search string; you can disable symbol search by typing M-s _ again.  In incremental symbol search, while you are typing the search string, only the beginning of the search string is required to match the beginning of a symbol, and ‘Pending’ appears in the search prompt until you use a search repeating key like C-s.

To begin a nonincremental symbol search, type M-s _ RET for a forward search, or M-s _ C-r RET or a backward search.  In nonincremental symbol searches, the beginning and end of the search string are required to match the beginning and end of a symbol, respectively.

The symbol search commands don’t perform character folding, and toggling lax whitespace matching (see [lax space matching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) has no effect on them.



### 17.5 Regular Expression Search



A *regular expression* (or *regexp* for short) is a pattern that denotes a class of alternative strings to match.  Emacs provides both incremental and nonincremental ways to search for a match for a regexp.  The syntax of regular expressions is explained in the next section.

- C-M-s

  Begin incremental regexp search (`isearch-forward-regexp`).

- C-M-r

  Begin reverse incremental regexp search (`isearch-backward-regexp`).



Incremental search for a regexp is done by typing C-M-s (`isearch-forward-regexp`), by invoking C-s with a prefix argument (whose value does not matter), or by typing M-r within a forward incremental search.  This command reads a search string incrementally just like C-s, but it treats the search string as a regexp rather than looking for an exact match against the text in the buffer.  Each time you add text to the search string, you make the regexp longer, and the new regexp is searched for.  To search backward for a regexp, use C-M-r (`isearch-backward-regexp`), C-r with a prefix argument, or M-r within a backward incremental search.



All of the special key sequences in an ordinary incremental search (see [Special Input for Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Special-Isearch.html)) do similar things in an incremental regexp search.  For instance, typing C-s immediately after starting the search retrieves the last incremental search regexp used and searches forward for it.  Incremental regexp and non-regexp searches have independent defaults.  They also have separate search rings, which you can access with M-p and M-n.  The maximum number of search regexps saved in the search ring is determined by the value of `regexp-search-ring-max`, 16 by default.

Unlike ordinary incremental search, incremental regexp search does not use lax space matching by default.  To toggle this feature use M-s SPC (`isearch-toggle-lax-whitespace`). Then any SPC typed in incremental regexp search will match any sequence of one or more whitespace characters.  The variable `search-whitespace-regexp` specifies the regexp for the lax space matching.  See [Special Input for Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Special-Isearch.html).

Also unlike ordinary incremental search, incremental regexp search cannot use character folding (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)).  (If you toggle character folding during incremental regexp search with M-s ', the search becomes a non-regexp search and the search pattern you typed is interpreted as a literal string.)



In some cases, adding characters to the regexp in an incremental regexp search can make the cursor move back and start again.  For example, if you have searched for ‘foo’ and you add ‘\|bar’, the cursor backs up in case the first ‘bar’ precedes the first ‘foo’.  (The prompt will change to say “Pending” to notify the user that this recalculation has happened.)   See [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html).

Forward and backward regexp search are not symmetrical, because regexp matching in Emacs always operates forward, starting with the beginning of the regexp.  Thus, forward regexp search scans forward, trying a forward match at each possible starting position.  Backward regexp search scans backward, trying a forward match at each possible starting position.  These search methods are not mirror images.



Nonincremental search for a regexp is done with the commands `re-search-forward` and `re-search-backward`.  You can invoke these with M-x, or by way of incremental regexp search with C-M-s RET and C-M-r RET.  When you invoke these commands with M-x, they search for the exact regexp you specify, and thus don’t support any lax-search features (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) except case folding.

If you use the incremental regexp search commands with a prefix argument, they perform ordinary string search, like `isearch-forward` and `isearch-backward`.  See [Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Incremental-Search.html).



### 17.6 Syntax of Regular Expressions



This section (and this manual in general) describes regular expression features that users typically use.  See [Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/elisp/Regular-Expressions.html#Regular-Expressions) in The Emacs Lisp Reference Manual, for additional features used mainly in Lisp programs.

Regular expressions have a syntax in which a few characters are *special constructs* and the rest are *ordinary*.  An ordinary character matches that same character and nothing else.  The special characters are ‘$^.*+?[\’.  The character ‘]’ is special if it ends a bracket expression (see below).  The character ‘-’ is special inside a bracket expression.  Any other character appearing in a regular expression is ordinary, unless a ‘\’ precedes it.  (When you use regular expressions in a Lisp program, each ‘\’ must be doubled, see the example near the end of this section.)

For example, ‘f’ is not a special character, so it is ordinary, and therefore ‘f’ is a regular expression that matches the string ‘f’ and no other string.  (It does *not* match the string ‘ff’.)  Likewise, ‘o’ is a regular expression that matches only ‘o’.  (When case distinctions are being ignored, these regexps also match ‘F’ and ‘O’, but we consider this a generalization of “the same string”, rather than an exception.)

Any two regular expressions a and b can be concatenated. The result is a regular expression which matches a string if a matches some amount of the beginning of that string and b matches the rest of the string.  As a trivial example, concatenating the regular expressions ‘f’ and ‘o’ gives the regular expression ‘fo’, which matches only the string ‘fo’.  To do something less trivial, you need to use one of the special characters. Here is a list of them.

- . (Period)

  is a special character that matches any single character except a newline.  For example, the regular expressions ‘a.b’ matches any three-character string that begins with ‘a’ and ends with ‘b’.

- *

  is not a construct by itself; it is a postfix operator that means to match the preceding regular expression repetitively any number of times, as many times as possible.  Thus, ‘o*’ matches any number of ‘o’s, including no ‘o’s. ‘*’ always applies to the *smallest* possible preceding expression.  Thus, ‘fo*’ has a repeating ‘o’, not a repeating ‘fo’.  It matches ‘f’, ‘fo’, ‘foo’, and so on. The matcher processes a ‘*’ construct by matching, immediately, as many repetitions as can be found.  Then it continues with the rest of the pattern.  If that fails, backtracking occurs, discarding some of the matches of the ‘*’-modified construct in case that makes it possible to match the rest of the pattern.  For example, in matching ‘ca*ar’ against the string ‘caaar’, the ‘a*’ first tries to match all three ‘a’s; but the rest of the pattern is ‘ar’ and there is only ‘r’ left to match, so this try fails. The next alternative is for ‘a*’ to match only two ‘a’s. With this choice, the rest of the regexp matches successfully.

- +

  is a postfix operator, similar to ‘*’ except that it must match the preceding expression at least once.  Thus, ‘ca+r’ matches the strings ‘car’ and ‘caaaar’ but not the string ‘cr’, whereas ‘ca*r’ matches all three strings.

- ?

  is a postfix operator, similar to ‘*’ except that it can match the preceding expression either once or not at all.  Thus, ‘ca?r’ matches ‘car’ or ‘cr’, and nothing else.

- *?, +?, ??[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html#index-non_002dgreedy-regexp-matching)

  are non-*greedy* variants of the operators above.  The normal operators ‘*’, ‘+’, ‘?’ match as much as they can, as long as the overall regexp can still match.  With a following ‘?’, they will match as little as possible. Thus, both ‘ab*’ and ‘ab*?’ can match the string ‘a’ and the string ‘abbbb’; but if you try to match them both against the text ‘abbb’, ‘ab*’ will match it all (the longest valid match), while ‘ab*?’  will match just ‘a’ (the shortest valid match). Non-greedy operators match the shortest possible string starting at a given starting point; in a forward search, though, the earliest possible starting point for match is always the one chosen.  Thus, if you search for ‘a.*?$’ against the text ‘abbab’ followed by a newline, it matches the whole string.  Since it *can* match starting at the first ‘a’, it does.

- [ … ]

  is a *bracket expression* (a.k.a. *set of alternative characters*), which matches one of a set of characters. In the simplest case, the characters between the two brackets are what this set can match.  Thus, ‘[ad]’ matches either one ‘a’ or one ‘d’, and ‘[ad]*’ matches any string composed of just ‘a’s and ‘d’s (including the empty string).  It follows that ‘c[ad]*r’ matches ‘cr’, ‘car’, ‘cdr’, ‘caddaar’, etc. You can also include character ranges in a character set, by writing the starting and ending characters with a ‘-’ between them.  Thus, ‘[a-z]’ matches any lower-case ASCII letter.  Ranges may be intermixed freely with individual characters, as in ‘[a-z$%.]’, which matches any lower-case ASCII letter or ‘$’, ‘%’ or period.  As another example, ‘[α-ωί]’ matches all lower-case Greek letters.  You can also include certain special *character classes* in a character set.  A ‘[:’ and balancing ‘:]’ enclose a character class inside a bracket expression.  For instance, ‘[[:alnum:]]’ matches any letter or digit.  See [Char Classes](https://www.gnu.org/software/emacs/manual/html_node/elisp/Char-Classes.html#Char-Classes) in The Emacs Lisp Reference Manual, for a list of character classes. To include a ‘]’ in a character set, you must make it the first character. For example, ‘[]a]’ matches ‘]’ or ‘a’.  To include a ‘-’, write ‘-’ as the last character of the set, tho you can also put it first or after a range.  Thus, ‘[]-]’ matches both ‘]’ and ‘-’. To include ‘^’ in a set, put it anywhere but at the beginning of the set.  (At the beginning, it complements the set—see below.) When you use a range in case-insensitive search, you should write both ends of the range in upper case, or both in lower case, or both should be non-letters.  The behavior of a mixed-case range such as ‘A-z’ is somewhat ill-defined, and it may change in future Emacs versions.

- [^ … ]

  ‘[^’ begins a *complemented character set*, which matches any character except the ones specified.  Thus, ‘[^a-z0-9A-Z]’ matches all characters *except* ASCII letters and digits. ‘^’ is not special in a character set unless it is the first character.  The character following the ‘^’ is treated as if it were first (in other words, ‘-’ and ‘]’ are not special there). A complemented character set can match a newline, unless newline is mentioned as one of the characters not to match.  This is in contrast to the handling of regexps in programs such as `grep`.

- ^

  is a special character that matches the empty string, but only at the beginning of a line in the text being matched.  Otherwise it fails to match anything.  Thus, ‘^foo’ matches a ‘foo’ that occurs at the beginning of a line. For historical compatibility reasons, ‘^’ can be used with this meaning only at the beginning of the regular expression, or after ‘\(’ or ‘\|’.

- $

  is similar to ‘^’ but matches only at the end of a line.  Thus, ‘x+$’ matches a string of one ‘x’ or more at the end of a line. For historical compatibility reasons, ‘$’ can be used with this meaning only at the end of the regular expression, or before ‘\)’ or ‘\|’.

- \

  has two functions: it quotes the special characters (including ‘\’), and it introduces additional special constructs. Because ‘\’ quotes special characters, ‘\$’ is a regular expression that matches only ‘$’, and ‘\[’ is a regular expression that matches only ‘[’, and so on. See the following section for the special constructs that begin with ‘\’.

Note: for historical compatibility, special characters are treated as ordinary ones if they are in contexts where their special meanings make no sense.  For example, ‘*foo’ treats ‘*’ as ordinary since there is no preceding expression on which the ‘*’ can act.  It is poor practice to depend on this behavior; it is better to quote the special character anyway, regardless of where it appears.

As a ‘\’ is not special inside a bracket expression, it can never remove the special meaning of ‘-’, ‘^’ or ‘]’. You should not quote these characters when they have no special meaning.  This would not clarify anything, since backslashes can legitimately precede these characters where they *have* special meaning, as in ‘[^\]’ (`"[^\\]"` for Lisp string syntax), which matches any single character except a backslash.



### 17.7 Backslash in Regular Expressions

For the most part, ‘\’ followed by any character matches only that character.  However, there are several exceptions: two-character sequences starting with ‘\’ that have special meanings.  The second character in the sequence is always an ordinary character when used on its own.  Here is a table of ‘\’ constructs.

- \|

  specifies an alternative.  Two regular expressions a and b with ‘\|’ in between form an expression that matches some text if either a matches it or b matches it.  It works by trying to match a, and if that fails, by trying to match b. Thus, ‘foo\|bar’ matches either ‘foo’ or ‘bar’ but no other string. ‘\|’ applies to the largest possible surrounding expressions.  Only a surrounding ‘\( … \)’ grouping can limit the grouping power of ‘\|’. Full backtracking capability exists to handle multiple uses of ‘\|’.

- \( … \)

  is a grouping construct that serves three purposes:  To enclose a set of ‘\|’ alternatives for other operations. Thus, ‘\(foo\|bar\)x’ matches either ‘foox’ or ‘barx’.  To enclose a complicated expression for the postfix operators ‘*’, ‘+’ and ‘?’ to operate on.  Thus, ‘ba\(na\)*’ matches ‘bananana’, etc., with any (zero or more) number of ‘na’ strings.  To record a matched substring for future reference.  This last application is not a consequence of the idea of a parenthetical grouping; it is a separate feature that is assigned as a second meaning to the same ‘\( … \)’ construct.  In practice there is usually no conflict between the two meanings; when there is a conflict, you can use a shy group, described below.

- \(?: … \)[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Backslash.html#index-shy-group_002c-in-regexp)

  specifies a *shy group* that does not record the matched substring; you can’t refer back to it with ‘\d’ (see below).  This is useful in mechanically combining regular expressions, so that you can add groups for syntactic purposes without interfering with the numbering of the groups that are meant to be referred to.

- \d[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Backslash.html#index-back-reference_002c-in-regexp)

  matches the same text that matched the dth occurrence of a ‘\( … \)’ construct.  This is called a *back reference*. After the end of a ‘\( … \)’ construct, the matcher remembers the beginning and end of the text matched by that construct. Then, later on in the regular expression, you can use ‘\’ followed by the digit d to mean “match the same text matched the dth ‘\( … \)’ construct”. The strings matching the first nine ‘\( … \)’ constructs appearing in a regular expression are assigned numbers 1 through 9 in the order that the open-parentheses appear in the regular expression. So you can use ‘\1’ through ‘\9’ to refer to the text matched by the corresponding ‘\( … \)’ constructs. For example, ‘\(.*\)\1’ matches any newline-free string that is composed of two identical halves.  The ‘\(.*\)’ matches the first half, which may be anything, but the ‘\1’ that follows must match the same exact text. If a particular ‘\( … \)’ construct matches more than once (which can easily happen if it is followed by ‘*’), only the last match is recorded.

- \{m\}

  is a postfix operator specifying m repetitions—that is, the preceding regular expression must match exactly m times in a row.  For example, ‘x\{4\}’ matches the string ‘xxxx’ and nothing else.

- \{m,n\}

  is a postfix operator specifying between m and n repetitions—that is, the preceding regular expression must match at least m times, but no more than n times.  If n is omitted, then there is no upper limit, but the preceding regular expression must match at least m times.  ‘\{0,1\}’ is equivalent to ‘?’.   ‘\{0,\}’ is equivalent to ‘*’.   ‘\{1,\}’ is equivalent to ‘+’.

- \`

  matches the empty string, but only at the beginning of the string or buffer (or its accessible portion) being matched against.

- \'

  matches the empty string, but only at the end of the string or buffer (or its accessible portion) being matched against.

- \=

  matches the empty string, but only at point.

- \b

  matches the empty string, but only at the beginning or end of a word.  Thus, ‘\bfoo\b’ matches any occurrence of ‘foo’ as a separate word.  ‘\bballs?\b’ matches ‘ball’ or ‘balls’ as a separate word. ‘\b’ matches at the beginning or end of the buffer regardless of what text appears next to it.

- \B

  matches the empty string, but *not* at the beginning or end of a word.

- \<

  matches the empty string, but only at the beginning of a word. ‘\<’ matches at the beginning of the buffer only if a word-constituent character follows.

- \>

  matches the empty string, but only at the end of a word.  ‘\>’ matches at the end of the buffer only if the contents end with a word-constituent character.

- \w

  matches any word-constituent character.  The syntax table determines which characters these are.  See [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual.

- \W

  matches any character that is not a word-constituent.

- \_<

  matches the empty string, but only at the beginning of a symbol. A symbol is a sequence of one or more symbol-constituent characters. A symbol-constituent character is a character whose syntax is either ‘w’ or ‘_’.  ‘\_<’ matches at the beginning of the buffer only if a symbol-constituent character follows.  As with words, the syntax table determines which characters are symbol-constituent.

- \_>

  matches the empty string, but only at the end of a symbol.  ‘\_>’ matches at the end of the buffer only if the contents end with a symbol-constituent character.

- \sc

  matches any character whose syntax is c.  Here c is a character that designates a particular syntax class: thus, ‘w’ for word constituent, ‘-’ or ‘ ’ for whitespace, ‘.’ for ordinary punctuation, etc.  See [Syntax Class Table](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Class-Table.html#Syntax-Class-Table) in The Emacs Lisp Reference Manual.

- \Sc

  matches any character whose syntax is not c.

- \cc

  matches any character that belongs to the category c.  For example, ‘\cc’ matches Chinese characters, ‘\cg’ matches Greek characters, etc.  For the description of the known categories, type M-x describe-categories RET.

- \Cc

  matches any character that does *not* belong to category c.

The constructs that pertain to words and syntax are controlled by the setting of the syntax table.  See [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual.



### 17.8 Regular Expression Example

Here is an example of a regexp—similar to the regexp that Emacs uses, by default, to recognize the end of a sentence, not including the following space (i.e., the variable `sentence-end-base`):

```
[.?!][]\"')}]*
```

This contains two parts in succession: a character set matching period, ‘?’, or ‘!’, and a character set matching close-brackets, quotes, or parentheses, repeated zero or more times.



### 17.9 Lax Matching During Searching



Normally, you’d want search commands to disregard certain minor differences between the search string you type and the text being searched.  For example, sequences of whitespace characters of different length are usually perceived as equivalent; letter-case differences usually don’t matter; etc.  This is known as *character equivalence*.

This section describes the Emacs lax search features, and how to tailor them to your needs.



By default, search commands perform *lax space matching*: each space, or sequence of spaces, matches any sequence of one or more whitespace characters in the text.  More precisely, Emacs matches each sequence of space characters in the search string to a regular expression specified by the user option `search-whitespace-regexp`.  The default value of this option considers any sequence of spaces and tab characters as whitespace. Hence, ‘foo bar’ matches ‘foo bar’, ‘foo bar’, ‘foo  bar’, and so on (but not ‘foobar’).  If you want to make spaces match sequences of newlines as well as spaces and tabs, customize the option to make its value be the regular expression ‘[ \t\n]+’.  (The default behavior of the incremental regexp search is different; see [Regular Expression Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Search.html).)

If you want whitespace characters to match exactly, you can turn lax space matching off by typing M-s SPC (`isearch-toggle-lax-whitespace`) within an incremental search. Another M-s SPC turns lax space matching back on.  To disable lax whitespace matching for all searches, change `search-whitespace-regexp` to `nil`; then each space in the search string matches exactly one space.



Searches in Emacs by default ignore the case of the text they are searching through, if you specify the search string in lower case. Thus, if you specify searching for ‘foo’, then ‘Foo’ and ‘fOO’ also match.  Regexps, and in particular character sets, behave likewise: ‘[ab]’ matches ‘a’ or ‘A’ or ‘b’ or ‘B’.  This feature is known as *case folding*, and it is supported in both incremental and non-incremental search modes.



An upper-case letter anywhere in the search string makes the search case-sensitive.  Thus, searching for ‘Foo’ does not find ‘foo’ or ‘FOO’.  This applies to regular expression search as well as to literal string search.  The effect ceases if you delete the upper-case letter from the search string.  The variable `search-upper-case` controls this: if it is non-`nil`, an upper-case character in the search string makes the search case-sensitive; setting it to `nil` disables this effect of upper-case characters.  The default value of this variable is `not-yanks`, which makes search case-sensitive if there are upper-case letters in the search string, and also causes text yanked into the search string (see [Isearch Yanking](https://www.gnu.org/software/emacs/manual/html_node/emacs/Isearch-Yank.html)) to be down-cased, so that such searches are case-insensitive by default.



If you set the variable `case-fold-search` to `nil`, then all letters must match exactly, including case.  This is a per-buffer variable; altering the variable normally affects only the current buffer, unless you change its default value.  See [Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Locals.html). This variable applies to nonincremental searches also, including those performed by the replace commands (see [Replacement Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Replace.html)) and the minibuffer history matching commands (see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html)).



Typing M-c or M-s c (`isearch-toggle-case-fold`) within an incremental search toggles the case sensitivity of that search.  The effect does not extend beyond the current incremental search, but it does override the effect of adding or removing an upper-case letter in the current search.

Several related variables control case-sensitivity of searching and matching for specific commands or activities.  For instance, `tags-case-fold-search` controls case sensitivity for `find-tag`.  To find these variables, do M-x apropos-variable RET case-fold-search RET.



Case folding disregards case distinctions among characters, making upper-case characters match lower-case variants, and vice versa.  A generalization of case folding is *character folding*, which disregards wider classes of distinctions among similar characters. For instance, under character folding the letter `a` matches all of its accented cousins like `ä` and `á`, i.e., the match disregards the diacritics that distinguish these variants.  In addition, `a` matches other characters that resemble it, or have it as part of their graphical representation, such as U+00AA FEMININE ORDINAL INDICATOR and U+24D0 CIRCLED LATIN SMALL LETTER A (which looks like a small `a` inside a circle). Similarly, the ASCII double-quote character `"` matches all the other variants of double quotes defined by the Unicode standard.  Finally, character folding can make a sequence of one or more characters match another sequence of a different length: for example, the sequence of two characters `ff` matches U+FB00 LATIN SMALL LIGATURE FF and the sequence `(a)` matches U+249C PARENTHESIZED LATIN SMALL LETTER A.  Character sequences that are not identical, but match under character folding are known as *equivalent character sequences*.



Generally, search commands in Emacs do not by default perform character folding in order to match equivalent character sequences. You can enable this behavior by customizing the variable `search-default-mode` to `char-fold-to-regexp`. See [Tailoring Search to Your Needs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html).  Within an incremental search, typing M-s ' (`isearch-toggle-char-fold`) toggles character folding, but only for that search.  (Replace commands have a different default, controlled by a separate option; see [Replace Commands and Lax Matches](https://www.gnu.org/software/emacs/manual/html_node/emacs/Replacement-and-Lax-Matches.html).)



By default, typing an explicit variant of a character, such as `ä`, as part of the search string doesn’t match its base character, such as `a`.  But if you customize the variable `char-fold-symmetric` to `t`, then search commands treat equivalent characters the same and use of any of a set of equivalent characters in a search string finds any of them in the text being searched, so typing an accented character `ä` matches the letter `a` as well as all the other variants like `á`.



You can add new foldings using the customizable variable `char-fold-include`, or remove the existing ones using the customizable variable `char-fold-exclude`.  You can also customize `char-fold-override` to `t` to disable all the character equivalences except those you add yourself using `char-fold-include`.



### 17.10 Replacement Commands



Emacs provides several commands for performing search-and-replace operations.  In addition to the simple M-x replace-string command, there is M-% (`query-replace`), which presents each occurrence of the search pattern and asks you whether to replace it.

The replace commands normally operate on the text from point to the end of the buffer.  When the region is active, they operate on it instead (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)).  The basic replace commands replace one *search string* (or regexp) with one *replacement string*.  It is possible to perform several replacements in parallel, using the command `expand-region-abbrevs` (see [Controlling Abbrev Expansion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Expanding-Abbrevs.html)).



#### 17.10.1 Unconditional Replacement



- M-x replace-string RET string RET newstring RET

  Replace every occurrence of string with newstring.

To replace every instance of ‘foo’ after point with ‘bar’, use the command M-x replace-string with the two arguments ‘foo’ and ‘bar’.  Replacement happens only in the text after point, so if you want to cover the whole buffer you must go to the beginning first.  All occurrences up to the end of the buffer are replaced; to limit replacement to part of the buffer, activate the region around that part.  When the region is active, replacement is limited to the region (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)).

When `replace-string` exits, it leaves point at the last occurrence replaced.  It adds the prior position of point (where the `replace-string` command was issued) to the mark ring, without activating the mark; use C-u C-SPC to move back there. See [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html).

A prefix argument restricts replacement to matches that are surrounded by word boundaries.

See [Replace Commands and Lax Matches](https://www.gnu.org/software/emacs/manual/html_node/emacs/Replacement-and-Lax-Matches.html), for details about case-sensitivity and character folding in replace commands.



#### 17.10.2 Regexp Replacement



The M-x replace-string command replaces exact matches for a single string.  The similar command M-x replace-regexp replaces any match for a specified regular expression pattern (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)).

- M-x replace-regexp RET regexp RET newstring RET

  Replace every match for regexp with newstring.



In `replace-regexp`, the newstring need not be constant: it can refer to all or part of what is matched by the regexp. ‘\&’ in newstring stands for the entire match being replaced.  ‘\d’ in newstring, where d is a digit starting from 1, stands for whatever matched the dth parenthesized grouping in regexp.  (This is called a “back reference”.)  ‘\#’ refers to the count of replacements already made in this command, as a decimal number.  In the first replacement, ‘\#’ stands for ‘0’; in the second, for ‘1’; and so on. For example,

```
M-x replace-regexp RET c[ad]+r RET \&-safe RET
```

replaces (for example) ‘cadr’ with ‘cadr-safe’ and ‘cddr’ with ‘cddr-safe’.

```
M-x replace-regexp RET \(c[ad]+r\)-safe RET \1 RET
```

performs the inverse transformation.  To include a ‘\’ in the text to replace with, you must enter ‘\\’.

If you want to enter part of the replacement string by hand each time, use ‘\?’ in the replacement string.  Each replacement will ask you to edit the replacement string in the minibuffer, putting point where the ‘\?’ was.

The remainder of this subsection is intended for specialized tasks and requires knowledge of Lisp.  Most readers can skip it.

You can use Lisp expressions to calculate parts of the replacement string.  To do this, write ‘\,’ followed by the expression in the replacement string.  Each replacement calculates the value of the expression and converts it to text without quoting (if it’s a string, this means using the string’s contents), and uses it in the replacement string in place of the expression itself.  If the expression is a symbol, one space in the replacement string after the symbol name goes with the symbol name, so the value replaces them both.

Inside such an expression, you can use some special sequences. ‘\&’ and ‘\d’ refer here, as usual, to the entire match as a string, and to a submatch as a string.  d may be multiple digits, and the value of ‘\d’ is `nil` if the d’th parenthesized grouping did not match.  You can also use ‘\#&’ and ‘\#d’ to refer to those matches as numbers (this is valid when the match or submatch has the form of a numeral). ‘\#’ here too stands for the number of already-completed replacements.

For example, we can exchange ‘x’ and ‘y’ this way:

```
M-x replace-regexp RET \(x\)\|y RET
\,(if \1 "y" "x") RET
```

For computing replacement strings for ‘\,’, the `format` function is often useful (see [Formatting Strings](https://www.gnu.org/software/emacs/manual/html_node/elisp/Formatting-Strings.html#Formatting-Strings) in The Emacs Lisp Reference Manual).  For example, to add consecutively numbered strings like ‘ABC00042’ to columns 73 to 80 (unless they are already occupied), you can use

```
M-x replace-regexp RET ^.\{0,72\}$ RET
\,(format "%-72sABC%05d" \& \#) RET
```

#### 17.10.3 Replace Commands and Lax Matches

This subsection describes the behavior of replace commands with respect to lax matches (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) and how to customize it. In general, replace commands mostly default to stricter matching than their search counterparts.



Unlike incremental search, the replacement commands do not use lax space matching (see [lax space matching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) by default. To enable lax space matching for replacement, change the variable `replace-lax-whitespace` to non-`nil`.  (This only affects how Emacs finds the text to replace, not the replacement text.)



A companion variable `replace-regexp-lax-whitespace` controls whether `query-replace-regexp` uses lax whitespace matching when searching for patterns.



If the first argument of a replace command is all lower case, the command ignores case while searching for occurrences to replace—provided `case-fold-search` is non-`nil` and `search-upper-case` is also non-`nil`.  If `search-upper-case` (see [search-upper-case](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) is `nil`, whether searching ignores case is determined by `case-fold-search` alone, regardless of letter-case of the command’s first argument.  If `case-fold-search` is set to `nil`, case is always significant in all searches.



In addition, when the second argument of a replace command is all or partly lower case, replacement commands try to preserve the case pattern of each occurrence.  Thus, the command

```
M-x replace-string RET foo RET bar RET
```

replaces a lower case ‘foo’ with a lower case ‘bar’, an all-caps ‘FOO’ with ‘BAR’, and a capitalized ‘Foo’ with ‘Bar’.  (These three alternatives—lower case, all caps, and capitalized, are the only ones that `replace-string` can distinguish.)  Note that Emacs decides whether to up-case or capitalize the replacement text by analyzing each word in the text being replaced, and will preserve the letter-case of the replaced text only if *all* of its words use the same letter-case.  Thus, the command

```
M-x replace-string RET foo bar RET baz quux RET
```

replaces ‘Foo Bar’ with ‘Baz Quux’ because both words in ‘Foo Bar’ are capitalized.  By contrast, the same command replaces ‘Foo bar’ with ‘baz quux’, i.e. it leaves the letter-case of the replacement text unchanged, since the two words in ‘Foo bar’ use different capitalization.  What exactly is considered a “word” depends on the syntax tables that are in effect in the current buffer (see [Syntax Tables](https://www.gnu.org/software/emacs/manual/html_node/elisp/Syntax-Tables.html#Syntax-Tables) in The Emacs Lisp Reference Manual); thus, ‘Foo_Bar’ is two words in Text mode, but could be a single word in some major mode that supports a programming language.

If upper-case letters are used in the replacement string, they remain upper case every time that text is inserted.  If upper-case letters are used in the first argument, the second argument is always substituted exactly as given, with no case conversion.  Likewise, if either `case-replace` or `case-fold-search` is set to `nil`, replacement is done without case conversion.



The replacement commands by default do not use character folding (see [character folding](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)) when looking for the text to replace.  To enable character folding for matching in `query-replace` and `replace-string`, set the variable `replace-char-fold` to a non-`nil` value.  (This setting does not affect the replacement text, only how Emacs finds the text to replace.  It also doesn’t affect `replace-regexp`.)



#### 17.10.4 Query Replace



- M-% string RET newstring RET

  Replace some occurrences of string with newstring.

- C-M-% regexp RET newstring RET

  Replace some matches for regexp with newstring.



If you want to change only some of the occurrences of ‘foo’ to ‘bar’, not all of them, use M-% (`query-replace`). This command finds occurrences of ‘foo’ one by one, displays each occurrence and asks you whether to replace it.  Aside from querying, `query-replace` works just like `replace-string` (see [Unconditional Replacement](https://www.gnu.org/software/emacs/manual/html_node/emacs/Unconditional-Replace.html)).  In particular, it preserves case provided that `case-replace` is non-`nil`, as it normally is (see [Replace Commands and Lax Matches](https://www.gnu.org/software/emacs/manual/html_node/emacs/Replacement-and-Lax-Matches.html)).  A numeric argument means to consider only occurrences that are bounded by word-delimiter characters.  A negative prefix argument replaces backward.



C-M-% performs regexp search and replace (`query-replace-regexp`). It works like `replace-regexp` except that it queries like `query-replace`.



You can reuse earlier replacements with these commands.  When `query-replace` or `query-replace-regexp` prompts for the search string, use M-p and M-n to show previous replacements in the form ‘from -> to’, where from is the search pattern, to is its replacement, and the separator between them is determined by the value of the variable `query-replace-from-to-separator`.  Type RET to select the desired replacement.  If the value of this variable is `nil`, replacements are not added to the command history, and cannot be reused.



These commands highlight the current match using the face `query-replace`.  You can disable this highlight by setting the variable `query-replace-highlight` to `nil`.  They highlight other matches using `lazy-highlight` just like incremental search (see [Incremental Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Incremental-Search.html)); this can be disabled by setting `query-replace-lazy-highlight` to `nil`.  By default, `query-replace-regexp` will show the substituted replacement string for the current match in the minibuffer.  If you want to keep special sequences ‘\&’ and ‘\n’ unexpanded, customize `query-replace-show-replacement` variable. Like `search-highlight-submatches` highlights subexpressions in incremental search (see [Tailoring Search to Your Needs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html)), the variable `query-replace-highlight-submatches` defines whether to highlight subexpressions in the regexp replacement commands.



The variable `query-replace-skip-read-only`, if set non-`nil`, will cause replacement commands to ignore matches in read-only text.  The default is not to ignore them.

The characters you can type when you are shown a match for the string or regexp are:

- SPC

- y

  to replace the occurrence with newstring.

- DEL

- Delete

- BACKSPACE

- n

  to skip to the next occurrence without replacing this one.

- , (Comma)

  to replace this occurrence and display the result.  You are then asked for another input character to say what to do next.  Since the replacement has already been made, DEL and SPC are equivalent in this situation; both move to the next occurrence. You can type C-r at this point (see below) to alter the replaced text.  You can also undo the replacement with the `undo` command (e.g., type C-x u; see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html)); this exits the `query-replace`, so if you want to do further replacement you must use C-x ESC ESC RET to restart (see [Repeating Minibuffer Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Repetition.html)).

- RET

- q

  to exit without doing any more replacements.

- . (Period)

  to replace this occurrence and then exit without searching for more occurrences.

- !

  to replace all remaining occurrences without asking again.

- ^

  to go back to the position of the previous occurrence (or what used to be an occurrence), in case you changed it by mistake or want to reexamine it.

- u

  to undo the last replacement and go back to where that replacement was made.

- U

  to undo all the replacements and go back to where the first replacement was made.

- C-r

  to enter a recursive editing level, in case the occurrence needs to be edited rather than just replaced with newstring.  When you are done, exit the recursive editing level with C-M-c to proceed to the next occurrence.  See [Recursive Editing Levels](https://www.gnu.org/software/emacs/manual/html_node/emacs/Recursive-Edit.html).

- C-w

  to delete the occurrence, and then enter a recursive editing level as in C-r.  Use the recursive edit to insert text to replace the deleted occurrence of string.  When done, exit the recursive editing level with C-M-c to proceed to the next occurrence.

- e

  to edit the replacement string in the minibuffer.  When you exit the minibuffer by typing RET, the minibuffer contents replace the current occurrence of the pattern.  They also become the new replacement string for any further occurrences.

- E

  is like e, but the next replacement will be done with exact case.  I.e., if you have a `query-replace` from ‘foo’ to ‘bar’, a text like ‘Foo’ will be normally be replaced with ‘Bar’.  Use this command to do the current replacement with exact case.

- C-l

  to redisplay the screen.  Then you must type another character to specify what to do with this occurrence.

- Y (Upper-case)

  to replace all remaining occurrences in all remaining buffers in multi-buffer replacements (like the Dired Q command that performs query replace on selected files).  It answers this question and all subsequent questions in the series with “yes”, without further user interaction.

- N (Upper-case)

  to skip to the next buffer in multi-buffer replacements without replacing remaining occurrences in the current buffer.  It answers this question “no”, gives up on the questions for the current buffer, and continues to the next buffer in the sequence.

- C-h

- ?

- F1

  to display a message summarizing these options.  Then you must type another character to specify what to do with this occurrence.

Aside from this, any other character exits the `query-replace`, and is then reread as part of a key sequence.  Thus, if you type C-k, it exits the `query-replace` and then kills to end of line.  In particular, C-g simply exits the `query-replace`.

To restart a `query-replace` once it is exited, use C-x ESC ESC, which repeats the `query-replace` because it used the minibuffer to read its arguments.  See [C-x ESC ESC](https://www.gnu.org/software/emacs/manual/html_node/emacs/Repetition.html).



The option `search-invisible` determines how `query-replace` treats invisible text.  See [Outline Search](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Visibility.html#Outline-Search).

See [Operating on Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Operating-on-Files.html), for the Dired Q command which performs query replace on selected files.  See also [Transforming File Names in Dired](https://www.gnu.org/software/emacs/manual/html_node/emacs/Transforming-File-Names.html), for Dired commands to rename, copy, or link files by replacing regexp matches in file names.



### 17.11 Other Search-and-Loop Commands

Here are some other commands that find matches for regular expressions.  They all ignore case in matching, if the pattern contains no upper-case letters and `case-fold-search` is non-`nil`. Aside from `multi-occur` and `multi-occur-in-matching-buffers`, which always search the whole buffer, all of the commands operate on the text from point to the end of the buffer, or on the region if it is active.

- M-x multi-isearch-buffers

  Prompt for one or more buffer names, ending with RET; then, begin a multi-buffer incremental search in those buffers.  (If the search fails in one buffer, the next C-s tries searching the next specified buffer, and so forth.)  With a prefix argument, prompt for a regexp and begin a multi-buffer incremental search in buffers matching that regexp.

- M-x multi-isearch-buffers-regexp

  This command is just like `multi-isearch-buffers`, except it performs an incremental regexp search.

- M-x multi-isearch-files

  Prompt for one or more file names, ending with RET; then, begin a multi-file incremental search in those files.  (If the search fails in one file, the next C-s tries searching the next specified file, and so forth.)  With a prefix argument, prompt for a regexp and begin a multi-file incremental search in files matching that regexp.

- M-x multi-isearch-files-regexp

  This command is just like `multi-isearch-files`, except it performs an incremental regexp search. In some modes that set the buffer-local variable `multi-isearch-next-buffer-function` (e.g., in Change Log mode) a multi-file incremental search is activated automatically.

- M-x occur

- M-s o

  Prompt for a regexp, and display a list showing each line in the buffer that contains a match for it.  If you type M-n at the prompt, you can reuse search strings from previous incremental searches.  The text that matched is highlighted using the `match` face.  A numeric argument n specifies that n lines of context are to be displayed before and after each matching line. The default number of context lines is specified by the variable `list-matching-lines-default-context-lines`.  When `list-matching-lines-jump-to-current-line` is non-`nil` the current line is shown highlighted with face `list-matching-lines-current-line-face` and the point is set at the first match after such line. You can also run M-s o when an incremental search is active; this uses the current search string. Note that matches for the regexp you type are extended to include complete lines, and a match that starts before the previous match ends is not considered a match.   The *Occur* buffer uses the Occur mode as its major mode.  You can use the n and p keys to move to the next or previous match; with prefix numeric argument, these commands move that many matches.  Digit keys are bound to `digit-argument`, so 5 n moves to the fifth next match (you don’t have to type C-u). SPC and DEL scroll the *Occur* buffer up and down. Clicking on a match or moving point there and typing RET visits the corresponding position in the original buffer that was searched. o and C-o display the match in another window; C-o does not select that window.  Alternatively, you can use the M-g M-n (`next-error`) command to visit the occurrences one by one (see [Compilation Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compilation-Mode.html)).  Finally, q quits the window showing the *Occur* buffer and buries the buffer.  Typing e in the *Occur* buffer makes the buffer writable and enters the Occur Edit mode, in which you can edit the matching lines and have those edits reflected in the text in the originating buffer.  Type C-c C-c to leave the Occur Edit mode and return to the Occur mode.  The command M-x list-matching-lines is a synonym for M-x occur.

- M-x multi-occur

  This command is just like `occur`, except it is able to search through multiple buffers.  It asks you to specify the buffer names one by one.

- M-x multi-occur-in-matching-buffers

  This command is similar to `multi-occur`, except the buffers to search are specified by a regular expression that matches visited file names.  With a prefix argument, it uses the regular expression to match buffer names instead.

- M-x how-many

  Prompt for a regexp, and print the number of matches for it in the buffer after point.  If the region is active, this operates on the region instead.

- M-x flush-lines

  Prompt for a regexp, and delete each line that contains a match for it, operating on the text after point.  When the command finishes, it prints the number of deleted matching lines. This command deletes the current line if it contains a match starting after point.  If the region is active, it operates on the region instead; if a line partially contained in the region contains a match entirely contained in the region, it is deleted. If a match is split across lines, `flush-lines` deletes all those lines.  It deletes the lines before starting to look for the next match; hence, it ignores a match starting on the same line at which another match ended.

- M-x keep-lines

  Prompt for a regexp, and delete each line that *does not* contain a match for it, operating on the text after point.  If point is not at the beginning of a line, this command always keeps the current line. If the region is active, the command operates on the region instead; it never deletes lines that are only partially contained in the region (a newline that ends a line counts as part of that line). If a match is split across lines, this command keeps all those lines.

- M-x kill-matching-lines

  Like `flush-lines`, but also add the matching lines to the kill ring.  The command adds the matching lines to the kill ring as a single string, including the newlines that separated the lines.

- M-x copy-matching-lines

  Like `kill-matching-lines`, but the matching lines are not removed from the buffer.



### 17.12 Tailoring Search to Your Needs



This section describes miscellaneous search-related customizations not described elsewhere.



The default search mode for the incremental search is specified by the variable `search-default-mode`.  It can be `nil`, `t`, or a function.  If it is `nil`, the default mode is to do literal searches without character folding, but with case folding and lax-whitespace matches as determined by `case-fold-search` and `search-whitespace-regexp`, respectively (see [Lax Matching During Searching](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lax-Search.html)).  If the value is `t`, incremental search defaults to regexp searches.  The default value specifies a function that only performs case folding and lax-whitespace matching.



The current match of an on-going incremental search is highlighted using the `isearch` face.  This highlighting can be disabled by setting the variable `search-highlight` to `nil`.



When searching for regular expressions (with C-M-s, for instance), subexpressions receive special highlighting depending on the `search-highlight-submatches` variable.  If this variable’s value is `nil`, no special highlighting is done, but if the value is non-`nil`, text that matches ‘\( … \)’ constructs (a.k.a. “subexpressions”) in the regular expression will be highlighted with distinct faces.  By default, two distinct faces are defined, named `isearch-group-1` and `isearch-group-2`. With these two faces, odd-numbered subexpressions will be highlighted using the `isearch-group-1` face and even-numbered subexpressions will be highlighted using the `isearch-group-2` face.  For instance, when searching for ‘foo-\([0-9]+\)\([a-z]+\)’, the part matched by ‘[0-9]+’ will be highlighted with the `isearch-group-1` face, and the part matched by ‘[a-z]+’ will be highlighted using `isearch-group-2`.  If you define additional faces using the same numbering scheme, i.e. `isearch-group-3`, `isearch-group-4`, …, then the face `isearch-group-M` will be used to highlight the M’th, `N+M`’th, `2N+M`’th, … subexpressions, where N is the total number of faces of the form `isearch-group-M`.



The other matches for the search string that are visible on display are highlighted using the `lazy-highlight` face.  Setting the variable `isearch-lazy-highlight` to `nil` disables this highlighting.  Here are some other variables that customize the lazy highlighting:

- `lazy-highlight-initial-delay`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-lazy_002dhighlight_002dinitial_002ddelay)

  Time in seconds to wait before highlighting visible matches. Applies only if the search string is less than `lazy-highlight-no-delay-length` characters long.

- `lazy-highlight-no-delay-length`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-lazy_002dhighlight_002dno_002ddelay_002dlength)

  For search strings at least as long as the value of this variable, lazy highlighting of matches starts immediately.

- `lazy-highlight-interval`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-lazy_002dhighlight_002dinterval)

  Time in seconds between highlighting successive matches.

- `lazy-highlight-max-at-a-time`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-lazy_002dhighlight_002dmax_002dat_002da_002dtime)

  The maximum number of matches to highlight before checking for input. A large number can take some time to highlight, so if you want to continue searching and type C-s or C-r during that time, Emacs will not respond until it finishes highlighting all those matches.  Thus, smaller values make Emacs more responsive.

- `isearch-lazy-count`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-isearch_002dlazy_002dcount)

  Show the current match number and the total number of matches in the search prompt.

- `lazy-count-prefix-format`[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search-Customizations.html#index-lazy_002dcount_002dprefix_002dformat)

- `lazy-count-suffix-format`

  These two variables determine the format of showing the current and the total number of matches for `isearch-lazy-count`.



Normally, entering RET within incremental search when the search string is empty launches a nonincremental search.  (Actually, it lets you edit the search string, and the next RET does the search.)  However, if you customize the variable `search-nonincremental-instead` to `nil`, typing RET will always exit the incremental search, even if the search string is empty.



By default, incremental search and query-replace commands match invisible text, but hide any such matches as soon as the current match moves off the invisible text.  If you customize the variable `isearch-hide-immediately` to `nil`, any invisible text where matches were found stays on display until the search or the replace command exits.



Searching incrementally on slow terminals, such as displays connected to remote machines over slow connection, could be annoying due to the need to redraw large portions of the display as the search proceeds.  Emacs provides a special display mode for slow terminals, whereby search pops up a separate small window and displays the text surrounding the match in that window.  Small windows display faster, so the annoying effect of slow speed is alleviated.  The variable `search-slow-speed` determines the baud rate threshold below which Emacs will use this display mode.  The variable `search-slow-window-lines` controls the number of lines in the window Emacs pops up for displaying the search results; the default is 1 line.  Normally, this window will pop up at the bottom of the window that displays the buffer where you start searching, but if the value of `search-slow-window-lines` is negative, that means to put the window at the top and give it the number of lines that is the absolute value of `search-slow-window-lines`.



## 18 Commands for Fixing Typos



In this chapter we describe commands that are useful when you catch a mistake while editing.  The most fundamental of these commands is the undo command C-/ (also bound to C-x u and C-_). This undoes a single command, or a part of a command (as in the case of `query-replace`), or several consecutive character insertions.  Consecutive repetitions of C-/ undo earlier and earlier changes, back to the limit of the undo information available.

Aside from the commands described here, you can erase text using deletion commands such as DEL (`delete-backward-char`). These were described earlier in this manual.  See [Erasing Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Erasing.html).



### 18.1 Undo



The *undo* command reverses recent changes in the buffer’s text. Each buffer records changes individually, and the undo command always applies to the current buffer.  You can undo all the changes in a buffer for as far back as the buffer’s records go.  Usually, each editing command makes a separate entry in the undo records, but some commands such as `query-replace` divide their changes into multiple entries for flexibility in undoing.  Consecutive character insertion commands are usually grouped together into a single undo record, to make undoing less tedious.

- C-/

- C-x u

- C-_

  Undo one entry in the current buffer’s undo records (`undo`).



To begin to undo, type C-/ (or its aliases, C-_ or C-x u)[6](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html#FOOT6).  This undoes the most recent change in the buffer, and moves point back to where it was before that change.  Consecutive repetitions of C-/ (or its aliases) undo earlier and earlier changes in the current buffer.  If all the recorded changes have already been undone, the undo command signals an error.



Any command other than an undo command breaks the sequence of undo commands.  Starting from that moment, the entire sequence of undo commands that you have just performed are themselves placed into the undo record.  Therefore, to re-apply changes you have undone, type C-f or any other command that harmlessly breaks the sequence of undoing; then type C-/ one or more times to undo some of the undo commands.

Alternatively, if you want to resume undoing, without redoing previous undo commands, use M-x undo-only.  This is like `undo`, but will not redo changes you have just undone.  To complement it, M-x undo-redo will undo previous undo commands (and will not record itself as an undoable command).

If you notice that a buffer has been modified accidentally, the easiest way to recover is to type C-/ repeatedly until the stars disappear from the front of the mode line (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)). Whenever an undo command makes the stars disappear from the mode line, it means that the buffer contents are the same as they were when the file was last read in or saved.  If you do not remember whether you changed the buffer deliberately, type C-/ once.  When you see the last change you made undone, you will see whether it was an intentional change.  If it was an accident, leave it undone.  If it was deliberate, redo the change as described above.

Alternatively, you can discard all the changes since the buffer was last visited or saved with M-x revert-buffer (see [Reverting a Buffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Reverting.html)).



When there is an active region, any use of `undo` performs *selective undo*: it undoes the most recent change within the region, instead of the entire buffer.  However, when Transient Mark mode is off (see [Disabling Transient Mark Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html)), C-/ always operates on the entire buffer, ignoring the region.  In this case, you can perform selective undo by supplying a prefix argument to the `undo` command: C-u C-/.  To undo further changes in the same region, repeat the `undo` command (no prefix argument is needed).

Some specialized buffers do not make undo records.  Buffers whose names start with spaces never do; these buffers are used internally by Emacs to hold text that users don’t normally look at or edit.



When the undo information for a buffer becomes too large, Emacs discards the oldest records from time to time (during *garbage collection*).  You can specify how much undo information to keep by setting the variables `undo-limit`, `undo-strong-limit`, and `undo-outer-limit`.  Their values are expressed in bytes.

The variable `undo-limit` sets a soft limit: Emacs keeps undo data for enough commands to reach this size, and perhaps exceed it, but does not keep data for any earlier commands beyond that.  Its default value is 160000.  The variable `undo-strong-limit` sets a stricter limit: any previous command (though not the most recent one) that pushes the size past this amount is forgotten.  The default value of `undo-strong-limit` is 240000.

Regardless of the values of those variables, the most recent change is never discarded unless it gets bigger than `undo-outer-limit` (normally 24,000,000).  At that point, Emacs discards the undo data and warns you about it.  This is the only situation in which you cannot undo the last command.  If this happens, you can increase the value of `undo-outer-limit` to make it even less likely to happen in the future.  But if you didn’t expect the command to create such large undo data, then it is probably a bug and you should report it. See [Reporting Bugs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Bugs.html).

> Aside from C-/, the `undo` command is also bound to C-x u because that is more straightforward for beginners to remember: ‘u’ stands for “undo”.  It is also bound to C-_ because typing C-/ on some text terminals actually enters C-_.



### 18.2 Transposing Text

- C-t

  Transpose two characters (`transpose-chars`).

- M-t

  Transpose two words (`transpose-words`).

- C-M-t

  Transpose two balanced expressions (`transpose-sexps`).

- C-x C-t

  Transpose two lines (`transpose-lines`).

- M-x transpose-sentences

  Transpose two sentences (`transpose-sentences`).

- M-x transpose-paragraphs

  Transpose two paragraphs (`transpose-paragraphs`).

- M-x transpose-regions

  Transpose two regions.



The common error of transposing two characters can be fixed, when they are adjacent, with the C-t command (`transpose-chars`).  Normally, C-t transposes the two characters on either side of point.  When given at the end of a line, rather than transposing the last character of the line with the newline, which would be useless, C-t transposes the last two characters on the line.  So, if you catch your transposition error right away, you can fix it with just a C-t.  If you don’t catch it so fast, you must move the cursor back between the two transposed characters before you type C-t.  If you transposed a space with the last character of the word before it, the word motion commands (M-f, M-b, etc.) are a good way of getting there. Otherwise, a reverse search (C-r) is often the best way. See [Searching and Replacement](https://www.gnu.org/software/emacs/manual/html_node/emacs/Search.html).



M-t transposes the word before point with the word after point (`transpose-words`).  It moves point forward over a word, dragging the word preceding or containing point forward as well.  The punctuation characters between the words do not move.  For example, ‘FOO, BAR’ transposes into ‘BAR, FOO’ rather than ‘BAR FOO,’.  When point is at the end of the line, it will transpose the word before point with the first word on the next line.



C-M-t (`transpose-sexps`) is a similar command for transposing two expressions (see [Expressions with Balanced Parentheses](https://www.gnu.org/software/emacs/manual/html_node/emacs/Expressions.html)), and C-x C-t (`transpose-lines`) exchanges lines.  M-x transpose-sentences and M-x transpose-paragraphs transpose sentences and paragraphs, respectively.  These commands work like M-t except as regards the units of text they transpose.

A numeric argument to a transpose command serves as a repeat count: it tells the transpose command to move the character (or word or expression or line) before or containing point across several other characters (or words or expressions or lines).  For example, C-u 3 C-t moves the character before point forward across three other characters.  It would change ‘f∗oobar’ into ‘oobf∗ar’.  This is equivalent to repeating C-t three times.  C-u - 4 M-t moves the word before point backward across four words.  C-u - C-M-t would cancel the effect of plain C-M-t.

A numeric argument of zero is assigned a special meaning (because otherwise a command with a repeat count of zero would do nothing): to transpose the character (or word or expression or line) ending after point with the one ending after the mark.



M-x transpose-regions transposes the text between point and mark with the text between the last two marks pushed to the mark ring (see [Setting the Mark](https://www.gnu.org/software/emacs/manual/html_node/emacs/Setting-Mark.html)).  With a numeric prefix argument, it transposes the text between point and mark with the text between two successive marks that many entries back in the mark ring.  This command is best used for transposing multiple characters (or words or sentences or paragraphs) in one go.



### 18.3 Case Conversion

- M-- M-l

  Convert last word to lower case.  Note Meta-- is Meta-minus.

- M-- M-u

  Convert last word to all upper case.

- M-- M-c

  Convert last word to lower case with capital initial.



A very common error is to type words in the wrong case.  Because of this, the word case-conversion commands M-l, M-u, and M-c have a special feature when used with a negative argument: they do not move the cursor.  As soon as you see you have mistyped the last word, you can simply case-convert it and go on typing.  See [Case Conversion Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Case.html).



### 18.4 Checking and Correcting Spelling



This section describes the commands to check the spelling of a single word or of a portion of a buffer.  These commands only work if a spelling checker program, one of Hunspell, Aspell, Ispell or Enchant, is installed.  These programs are not part of Emacs, but one of them is usually installed on GNU/Linux and other free operating systems. See [Aspell](http://aspell.net/man-html/index.html#Top) in The Aspell Manual.



If you have only one of the spelling checker programs installed, Emacs will find it when you invoke for the first time one of the commands described here.  If you have more than one of them installed, you can control which one is used by customizing the variable `ispell-program-name`.

- M-$

  Check and correct spelling of the word at point (`ispell-word`). If the region is active, do it for all words in the region instead.

- C-u M-$

  If a previous spelling operation was interrupted, continue that operation (`ispell-continue`).

- M-x ispell

  Check and correct spelling of all words in the buffer.  If the region is active, do it for all words in the region instead.

- M-x ispell-buffer

  Check and correct spelling in the buffer.

- M-x ispell-region

  Check and correct spelling in the region.

- M-x ispell-message

  Check and correct spelling in a draft mail message, excluding cited material.

- M-x ispell-comments-and-strings

  Check and correct spelling of comments and strings in the buffer or region.

- M-x ispell-comment-or-string-at-point

  Check the comment or string at point.

- M-x ispell-change-dictionary RET dict RET

  Restart the spell-checker process, using dict as the dictionary.

- M-x ispell-kill-ispell

  Kill the spell-checker subprocess.

- M-TAB

- ESC TAB

- C-M-i

  Complete the word before point based on the spelling dictionary and other completion sources (`completion-at-point`).

- M-x flyspell-mode

  Enable Flyspell mode, which highlights all misspelled words.

- M-x flyspell-prog-mode

  Enable Flyspell mode for comments and strings only.



To check the spelling of the word around or before point, and optionally correct it as well, type M-$ (`ispell-word`). If a region is active, M-$ checks the spelling of all words within the region.  See [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html).  (When Transient Mark mode is off, M-$ always acts on the word around or before point, ignoring the region; see [Disabling Transient Mark Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Disabled-Transient-Mark.html).)  When invoked with a prefix argument, C-u M-$, this calls `ispell-continue`, which continues the spelling operation, if any, which was interrupted with X or C-g.



Similarly, the command M-x ispell performs spell-checking in the region if one is active, or in the entire buffer otherwise.  The commands M-x ispell-buffer and M-x ispell-region explicitly perform spell-checking on the entire buffer or the region respectively.  To check spelling in an email message you are writing, use M-x ispell-message; that command checks the whole buffer, except for material that is indented or appears to be cited from other messages.  See [Sending Mail](https://www.gnu.org/software/emacs/manual/html_node/emacs/Sending-Mail.html).  When dealing with source code, you can use M-x ispell-comments-and-strings or M-x ispell-comment-or-string-at-point to check only comments or string literals.

When one of these commands encounters what appears to be an incorrect word, it asks you what to do.  It usually displays a list of numbered *near-misses*—words that are close to the incorrect word. Then you must type a single-character response.  Here are the valid responses:

- digit

  Replace the word, just this time, with one of the displayed near-misses.  Each near-miss is listed with a digit; type that digit to select it.

- SPC

  Skip this word—continue to consider it incorrect, but don’t change it here.

- r new RET

  Replace the word, just this time, with new.  (The replacement string will be rescanned for more spelling errors.)

- R new RET

  Replace the word with new, and do a `query-replace` so you can replace it elsewhere in the buffer if you wish.  (The replacements will be rescanned for more spelling errors.)

- a

  Accept the incorrect word—treat it as correct, but only in this editing session.

- A

  Accept the incorrect word—treat it as correct, but only in this editing session and for this buffer.

- i

  Insert this word in your personal dictionary file so that it will be considered correct from now on, even in future sessions.

- m

  Like i, but you can also specify dictionary completion information.

- u

  Insert the lower-case version of this word in your personal dictionary file.

- l word RET

  Look in the dictionary for words that match word.  These words become the new list of near-misses; you can select one of them as the replacement by typing a digit.  You can use ‘*’ in word as a wildcard.

- C-g

- X

  Interrupt the interactive spell-checking, leaving point at the word that was being checked.  You can restart checking again afterward with C-u M-$.

- x

  Quit interactive spell-checking and move point back to where it was when you started spell-checking.

- q

  Quit interactive spell-checking and kill the spell-checker subprocess.

- C-r

  Enter recursive-edit (see [Recursive Editing Levels](https://www.gnu.org/software/emacs/manual/html_node/emacs/Recursive-Edit.html)).  When you exit recursive-edit with C-M-c, the interactive spell-checking will resume.  This allows you to consult the buffer text without interrupting the spell-checking.  Do *not* modify the buffer in the recursive editing, and especially don’t modify the misspelled word, as the edits will be undone when you exit recursive-edit.  If you need to edit the misspelled word, use r or R instead, or use X, edit the buffer, then resume with C-u M-$.

- C-z

  Suspend Emacs or iconify the selected frame.

- ?

  Show the list of options.

Use the command M-TAB (`completion-at-point`) to complete the word at point.  Insert the beginning of a word, and then type M-TAB to select from a list of completions.  (If your window manager intercepts M-TAB, type ESC TAB or C-M-i.)



Once started, the spell-checker subprocess continues to run, waiting for something to do, so that subsequent spell-checking commands complete more quickly.  If you want to get rid of the process, use M-x ispell-kill-ispell.  This is not usually necessary, since the process uses no processor time except when you do spelling correction.



Spell-checkers look up spelling in two dictionaries: the standard dictionary and your personal dictionary.  The standard dictionary is specified by the variable `ispell-local-dictionary` or, if that is `nil`, by the variable `ispell-dictionary`. If both are `nil`, the spelling program’s default dictionary is used.  The command M-x ispell-change-dictionary sets the standard dictionary for the buffer and then restarts the subprocess, so that it will use a different standard dictionary.  Your personal dictionary is specified by the variable `ispell-personal-dictionary`.  If that is `nil`, the spelling program looks for a personal dictionary in a default location, which is specific to each spell-checker.



Usually, a dictionary used by a spell-checker is for a specific language.  The default language is determined from your system’s environment and locale.  Both the standard dictionary and your personal dictionary should be changed if you want to spell-check text in a different language.  You can use the `ispell-change-dictionary` command for that.



Hunspell is special in that it supports spell-checking using several different dictionaries in parallel.  To use this feature, invoke the M-x ispell-hunspell-add-multi-dic command before you start using Hunspell for a particular combination of dictionaries.  This command prompts for the dictionary combination, which should be a comma-separated list of language-specific dictionary names, such as ‘en_US,de_DE,ru_RU’.  Thereafter, you can spell-check text which mixes these languages without changing the dictionaries each time. (Caveat: when several languages use the same script, it is possible that a word that is mis-spelled in one language is found as a valid spelling in the dictionary of another language; in that case, the mis-spelled word might be missed.)



A separate dictionary is used for word completion.  The variable `ispell-complete-word-dict` specifies the file name of this dictionary.  The completion dictionary must be different because it cannot use the information about roots and affixes of the words, which spell-checking uses to detect variations of words.  For some languages, there is a spell-checking dictionary but no word completion dictionary.



Flyspell mode is a minor mode that performs automatic spell-checking of the text you type as you type it.  When it finds a word that it does not recognize, it highlights that word.  Type M-x flyspell-mode to toggle Flyspell mode in the current buffer.  To enable Flyspell mode in all text mode buffers, add `flyspell-mode` to `text-mode-hook`.  See [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html).  Note that, as Flyspell mode needs to check each word across which you move, it will slow down cursor motion and scrolling commands.  It also doesn’t automatically check the text you didn’t type or move across; use `flyspell-region` or `flyspell-buffer` for that.



Normally, Flyspell mode highlights misspelled words that you typed or modified, but also words you move across without changing them.  But if you customize the variable `flyspell-check-changes` to a non-`nil` value, Flyspell mode will check only the words you typed or edited in some way.



When Flyspell mode highlights a word as misspelled, you can click on it with mouse-2 (`flyspell-correct-word`) to display a menu of possible corrections and actions.  If you want this menu on mouse-3 instead, enable `context-menu-mode`.  In addition, C-. or ESC TAB (`flyspell-auto-correct-word`) will propose various successive corrections for the word at point, and C-c $ (`flyspell-correct-word-before-point`) will pop up a menu of possible corrections.  Of course, you can always correct the misspelled word by editing it manually in any way you like.



Flyspell Prog mode works just like ordinary Flyspell mode, except that it only checks words in comments and string constants.  This feature is useful for editing programs.  Type M-x flyspell-prog-mode to enable or disable this mode in the current buffer.  To enable this mode in all programming mode buffers, add `flyspell-prog-mode` to `prog-mode-hook` (see [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html)).



## 19 Keyboard Macros



In this chapter we describe how to record a sequence of editing commands so you can repeat it conveniently later.

A *keyboard macro* is a command defined by an Emacs user to stand for another sequence of keys.  For example, if you discover that you are about to type C-n M-d C-d forty times, you can speed your work by defining a keyboard macro to do C-n M-d C-d, and then executing it 39 more times.

You define a keyboard macro by executing and recording the commands which are its definition.  Put differently, as you define a keyboard macro, the definition is being executed for the first time.  This way, you can see the effects of your commands, so that you don’t have to figure them out in your head.  When you close the definition, the keyboard macro is defined and also has been, in effect, executed once. You can then do the whole thing over again by invoking the macro.

Keyboard macros differ from ordinary Emacs commands in that they are written in the Emacs command language rather than in Lisp.  This makes it easier for the novice to write them, and makes them more convenient as temporary hacks.  However, the Emacs command language is not powerful enough as a programming language to be useful for writing anything intelligent or general.  For such things, Lisp must be used.



### 19.1 Basic Use

- F3

  Start defining a keyboard macro (`kmacro-start-macro-or-insert-counter`).

- F4

  If a keyboard macro is being defined, end the definition; otherwise, execute the most recent keyboard macro (`kmacro-end-or-call-macro`).

- C-u F3

  Re-execute last keyboard macro, then append keys to its definition.

- C-u C-u F3

  Append keys to the last keyboard macro without re-executing it.

- C-x C-k r

  Run the last keyboard macro on each line that begins in the region (`apply-macro-to-region-lines`).

- C-x (

  Start defining a keyboard macro (old style) (`kmacro-start-macro`); with a prefix argument, append keys to the last macro.

- C-x )

  End a macro definition (old style) (`kmacro-end-macro`); prefix argument serves as the repeat count for executing the macro.

- C-x e

  Execute the most recently defined keyboard macro (`kmacro-end-and-call-macro`); prefix argument serves as repeat count.



To start defining a keyboard macro, type F3.  From then on, your keys continue to be executed, but also become part of the definition of the macro.  ‘Def’ appears in the mode line to remind you of what is going on.  When you are finished, type F4 (`kmacro-end-or-call-macro`) to terminate the definition.  For example,

```
F3 M-f foo F4
```

defines a macro to move forward a word and then insert ‘foo’. Note that F3 and F4 do not become part of the macro.

After defining the macro, you can call it with F4.  For the above example, this has the same effect as typing M-f foo again. (Note the two roles of the F4 command: it ends the macro if you are in the process of defining one, or calls the last macro otherwise.)  You can also supply F4 with a numeric prefix argument ‘n’, which means to invoke the macro ‘n’ times.  An argument of zero repeats the macro indefinitely, until it gets an error or you type C-g (or, on MS-DOS, C-Break).

The above example demonstrates a handy trick that you can employ with keyboard macros: if you wish to repeat an operation at regularly spaced places in the text, include a motion command as part of the macro.  In this case, repeating the macro inserts the string ‘foo’ after each successive word.

After terminating the definition of a keyboard macro, you can append more keystrokes to its definition by typing C-u F3.  This is equivalent to plain F3 followed by retyping the whole definition so far.  As a consequence, it re-executes the macro as previously defined.  If you change the variable `kmacro-execute-before-append` to `nil`, the existing macro will not be re-executed before appending to it (the default is `t`).  You can also add to the end of the definition of the last keyboard macro without re-executing it by typing C-u C-u F3.

When a command reads an argument with the minibuffer, your minibuffer input becomes part of the macro along with the command.  So when you replay the macro, the command gets the same argument as when you entered the macro.  For example,

```
F3 C-a C-k C-x b foo RET C-y C-x b RET F4
```

defines a macro that kills the current line, yanks it into the buffer ‘foo’, then returns to the original buffer.

Most keyboard commands work as usual in a keyboard macro definition, with some exceptions.  Typing C-g (`keyboard-quit`) quits the keyboard macro definition.  Typing C-M-c (`exit-recursive-edit`) can be unreliable: it works as you’d expect if exiting a recursive edit that started within the macro, but if it exits a recursive edit that started before you invoked the keyboard macro, it also necessarily exits the keyboard macro too. Mouse events are also unreliable, even though you can use them in a keyboard macro: when the macro replays the mouse event, it uses the original mouse position of that event, the position that the mouse had while you were defining the macro.  The effect of this may be hard to predict.



The command C-x C-k r (`apply-macro-to-region-lines`) repeats the last defined keyboard macro on each line that begins in the region.  It does this line by line, by moving point to the beginning of the line and then executing the macro.



In addition to the F3 and F4 commands described above, Emacs also supports an older set of key bindings for defining and executing keyboard macros.  To begin a macro definition, type C-x ( (`kmacro-start-macro`); as with F3, a prefix argument appends this definition to the last keyboard macro.  To end a macro definition, type C-x ) (`kmacro-end-macro`).  To execute the most recent macro, type C-x e (`kmacro-end-and-call-macro`).  If you enter C-x e while defining a macro, the macro is terminated and executed immediately. Immediately after typing C-x e, you can type e repeatedly to immediately repeat the macro one or more times.  You can also give C-x e a repeat argument, just like F4 (when it is used to execute a macro).

C-x ) can be given a repeat count as an argument.  This means to repeat the macro right after defining it.  The macro definition itself counts as the first repetition, since it is executed as you define it, so C-u 4 C-x ) executes the macro immediately 3 additional times.



While executing a long-running keyboard macro, it can sometimes be useful to trigger a redisplay (to show how far we’ve gotten).  The C-x C-k d command can be used for this.  As a not very useful example, C-x ( M-f C-x C-k d C-x ) will create a macro that will redisplay once per iteration when saying C-u 42 C-x e.

### 19.2 The Keyboard Macro Ring

All defined keyboard macros are recorded in the *keyboard macro ring*.  There is only one keyboard macro ring, shared by all buffers.

- C-x C-k C-k

  Execute the keyboard macro at the head of the ring (`kmacro-end-or-call-macro-repeat`).

- C-x C-k C-n

  Rotate the keyboard macro ring to the next macro (defined earlier) (`kmacro-cycle-ring-next`).

- C-x C-k C-p

  Rotate the keyboard macro ring to the previous macro (defined later) (`kmacro-cycle-ring-previous`).

All commands which operate on the keyboard macro ring use the same C-x C-k prefix.  Most of these commands can be executed and repeated immediately after each other without repeating the C-x C-k prefix.  For example,

```
C-x C-k C-p C-p C-k C-k C-k C-n C-n C-k C-p C-k C-d
```

will rotate the keyboard macro ring to the second-previous macro, execute the resulting head macro three times, rotate back to the original head macro, execute that once, rotate to the previous macro, execute that, and finally delete it from the macro ring.



The command C-x C-k C-k (`kmacro-end-or-call-macro-repeat`) executes the keyboard macro at the head of the macro ring.  You can repeat the macro immediately by typing another C-k, or you can rotate the macro ring immediately by typing C-n or C-p.

When a keyboard macro is being defined, C-x C-k C-k behaves like F4 except that, immediately afterward, you can use most key bindings of this section without the C-x C-k prefix.  For instance, another C-k will re-execute the macro.



The commands C-x C-k C-n (`kmacro-cycle-ring-next`) and C-x C-k C-p (`kmacro-cycle-ring-previous`) rotate the macro ring, bringing the next or previous keyboard macro to the head of the macro ring.  The definition of the new head macro is displayed in the echo area.  You can continue to rotate the macro ring immediately by repeating just C-n and C-p until the desired macro is at the head of the ring.  To execute the new macro ring head immediately, just type C-k.

Note that Emacs treats the head of the macro ring as the last defined keyboard macro.  For instance, F4 will execute that macro, and C-x C-k n will give it a name.



The maximum number of macros stored in the keyboard macro ring is determined by the customizable variable `kmacro-ring-max`.

### 19.3 The Keyboard Macro Counter

Each keyboard macro has an associated counter, which is initialized to 0 when you start defining the macro.  This *current counter* allows you to insert a number into the buffer that depends on the number of times the macro has been called.  The counter is normally incremented each time its value is inserted into the buffer.

In addition to the current counter, keyboard macros also maintain the *previous counter*, which records the value the current counter had last time it was incremented or set.  Note that incrementing the current counter by zero, e.g., with C-u 0 C-x C-k C-i, also records the value of the current counter as the previous counter value.

- F3

  In a keyboard macro definition, insert the keyboard macro counter value in the buffer (`kmacro-start-macro-or-insert-counter`).

- C-x C-k C-i

  Insert the keyboard macro counter value in the buffer (`kmacro-insert-counter`).

- C-x C-k C-c

  Set the keyboard macro counter (`kmacro-set-counter`).

- C-x C-k C-a

  Add the prefix arg to the keyboard macro counter (`kmacro-add-counter`).

- C-x C-k C-f

  Specify the format for inserting the keyboard macro counter (`kmacro-set-format`).



When you are defining a keyboard macro, the command F3 (`kmacro-start-macro-or-insert-counter`) inserts the current value of the keyboard macro’s counter into the buffer, and increments the counter by 1.  (If you are not defining a macro, F3 begins a macro definition instead.  See [Basic Use](https://www.gnu.org/software/emacs/manual/html_node/emacs/Basic-Keyboard-Macro.html).)  You can use a numeric prefix argument to specify a different increment.  If you just specify a C-u prefix, that inserts the previous counter value, and doesn’t change the current value.

As an example, let us show how the keyboard macro counter can be used to build a numbered list.  Consider the following key sequence:

```
F3 C-a F3 . SPC F4
```

As part of this keyboard macro definition, the string ‘0. ’ was inserted into the beginning of the current line.  If you now move somewhere else in the buffer and type F4 to invoke the macro, the string ‘1. ’ is inserted at the beginning of that line. Subsequent invocations insert ‘2. ’, ‘3. ’, and so forth.

The command C-x C-k C-i (`kmacro-insert-counter`) does the same thing as F3, but it can be used outside a keyboard macro definition.  When no keyboard macro is being defined or executed, it inserts and increments the counter of the macro at the head of the keyboard macro ring.



The command C-x C-k C-c (`kmacro-set-counter`) sets the current macro counter to the value of the numeric argument.  If you use it inside the macro, it operates on each repetition of the macro.  If you specify just C-u as the prefix, while executing the macro, that resets the counter to the value it had at the beginning of the current repetition of the macro (undoing any increments so far in this repetition).



The command C-x C-k C-a (`kmacro-add-counter`) adds the prefix argument to the current macro counter.  With just C-u as argument, it resets the counter to the last value inserted by any keyboard macro.  (Normally, when you use this, the last insertion will be in the same macro and it will be the same counter.)



The command C-x C-k C-f (`kmacro-set-format`) prompts for the format to use when inserting the macro counter.  The default format is ‘%d’, which means to insert the number in decimal without any padding.  You can exit with empty minibuffer to reset the format to this default.  You can specify any format string that the `format` function accepts and that makes sense with a single integer extra argument (see [Formatting Strings](https://www.gnu.org/software/emacs/manual/html_node/elisp/Formatting-Strings.html#Formatting-Strings) in The Emacs Lisp Reference Manual).  Do not put the format string inside double quotes when you insert it in the minibuffer.

If you use this command while no keyboard macro is being defined or executed, the new format affects all subsequent macro definitions. Existing macros continue to use the format in effect when they were defined.  If you set the format while defining a keyboard macro, this affects the macro being defined from that point on, but it does not affect subsequent macros.  Execution of the macro will, at each step, use the format in effect at that step during its definition.  Changes to the macro format during execution of a macro, like the corresponding changes during its definition, have no effect on subsequent macros.

The format set by C-x C-k C-f does not affect insertion of numbers stored in registers.

If you use a register as a counter, incrementing it on each repetition of the macro, that accomplishes the same thing as a keyboard macro counter.  See [Keeping Numbers in Registers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Number-Registers.html).  For most purposes, it is simpler to use a keyboard macro counter.



### 19.4 Executing Macros with Variations

In a keyboard macro, you can create an effect similar to that of `query-replace`, in that the macro asks you each time around whether to make a change.

- C-x q

  When this point is reached during macro execution, ask for confirmation (`kbd-macro-query`).



While defining the macro, type C-x q at the point where you want the query to occur.  During macro definition, the C-x q does nothing, but when you run the macro later, C-x q asks you interactively whether to continue.

The valid responses when C-x q asks are:

- SPC (or y)

  Continue executing the keyboard macro.

- DEL (or n)

  Skip the remainder of this repetition of the macro, and start right away with the next repetition.

- RET (or q)

  Skip the remainder of this repetition and cancel further repetitions.

- C-r

  Enter a recursive editing level, in which you can perform editing which is not part of the macro.  When you exit the recursive edit using C-M-c, you are asked again how to continue with the keyboard macro.  If you type a SPC at this time, the rest of the macro definition is executed.  It is up to you to leave point and the text in a state such that the rest of the macro will do what you want.

C-u C-x q, which is C-x q with a prefix argument, performs a completely different function.  It enters a recursive edit reading input from the keyboard, both when you type it during the definition of the macro, and when it is executed from the macro.  During definition, the editing you do inside the recursive edit does not become part of the macro.  During macro execution, the recursive edit gives you a chance to do some particularized editing on each repetition. See [Recursive Editing Levels](https://www.gnu.org/software/emacs/manual/html_node/emacs/Recursive-Edit.html).



### 19.5 Naming and Saving Keyboard Macros

- C-x C-k n

  Give a command name (for the duration of the Emacs session) to the most recently defined keyboard macro (`kmacro-name-last-macro`).

- C-x C-k b

  Bind the most recently defined keyboard macro to a key sequence (for the duration of the session) (`kmacro-bind-to-key`).

- M-x insert-kbd-macro

  Insert in the buffer a keyboard macro’s definition, as Lisp code.



If you wish to save a keyboard macro for later use, you can give it a name using C-x C-k n (`kmacro-name-last-macro`). This reads a name as an argument using the minibuffer and defines that name to execute the last keyboard macro, in its current form.  (If you later add to the definition of this macro, that does not alter the name’s definition as a macro.)  The macro name is a Lisp symbol, and defining it in this way makes it a valid command name for calling with M-x or for binding a key to with `keymap-global-set` (see [Keymaps](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keymaps.html)).  If you specify a name that has a prior definition other than a keyboard macro, an error message is shown and nothing is changed.



You can also bind the last keyboard macro (in its current form) to a key, using C-x C-k b (`kmacro-bind-to-key`) followed by the key sequence you want to bind.  You can bind to any key sequence in the global keymap, but since most key sequences already have other bindings, you should select the key sequence carefully.  If you try to bind to a key sequence with an existing binding (in any keymap), this command asks you for confirmation before replacing the existing binding.

To avoid problems caused by overriding existing bindings, the key sequences C-x C-k 0 through C-x C-k 9 and C-x C-k A through C-x C-k Z are reserved for your own keyboard macro bindings.  In fact, to bind to one of these key sequences, you only need to type the digit or letter rather than the whole key sequences. For example,

```
C-x C-k b 4
```

will bind the last keyboard macro to the key sequence C-x C-k 4.



Once a macro has a command name, you can save its definition in a file. Then it can be used in another editing session.  First, visit the file you want to save the definition in.  Then use this command:

```
M-x insert-kbd-macro RET macroname RET
```

This inserts some Lisp code that, when executed later, will define the same macro with the same definition it has now.  (You don’t need to understand Lisp code to do this, because `insert-kbd-macro` writes the Lisp code for you.)  Then save the file.  You can load the file later with `load-file` (see [Libraries of Lisp Code for Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lisp-Libraries.html)).  If the file you save in is your init file ~/.emacs (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)) then the macro will be defined each time you run Emacs.

If you give `insert-kbd-macro` a prefix argument, it makes additional Lisp code to record the keys (if any) that you have bound to macroname, so that the macro will be reassigned the same keys when you load the file.



### 19.6 Editing a Keyboard Macro

- C-x C-k C-e

  Edit the last defined keyboard macro (`kmacro-edit-macro`).

- C-x C-k e name RET

  Edit a previously defined keyboard macro name (`edit-kbd-macro`).

- C-x C-k l

  Edit the last 300 keystrokes as a keyboard macro (`kmacro-edit-lossage`).



You can edit the last keyboard macro by typing C-x C-k C-e or C-x C-k RET (`kmacro-edit-macro`).  This formats the macro definition in a buffer and enters a specialized major mode for editing it.  Type C-h m once in that buffer to display details of how to edit the macro.  When you are finished editing, type C-c C-c.



`edmacro-mode`, the major mode used by `kmacro-edit-macro`, provides commands for more easily editing the formatted macro.  Use C-c C-q (`edmacro-insert-key`) to insert the next key sequence that you type into the buffer using the correct format, similar to C-q (`quoted-insert`).  Use C-c C-r (`edmacro-set-macro-to-region-lines`) to replace the macro’s text with the text in the region.  If the region does not begin at the start of a line or if it does not end at the end of a line, the region is extended to include complete lines.  If the region ends at the beginning of a line, that final line is excluded.



You can edit a named keyboard macro or a macro bound to a key by typing C-x C-k e (`edit-kbd-macro`).  Follow that with the keyboard input that you would use to invoke the macro—C-x e or M-x name or some other key sequence.



You can edit the last 300 keystrokes as a macro by typing C-x C-k l (`kmacro-edit-lossage`).  By default, your most recent keystrokes are listed at the bottom of the buffer. To list a macro’s key sequences in reverse order, set `edmacro-reverse-macro-lines` to `t`.



### 19.7 Stepwise Editing a Keyboard Macro



You can interactively replay and edit the last keyboard macro, one command at a time, by typing C-x C-k SPC (`kmacro-step-edit-macro`).  Unless you quit the macro using q or C-g, the edited macro replaces the last macro on the macro ring.

This macro editing feature shows the last macro in the minibuffer together with the first (or next) command to be executed, and prompts you for an action.  You can enter ? to get a summary of your options.  These actions are available:

- SPC and y execute the current command, and advance to the next command in the keyboard macro.
- n, d, and DEL skip and delete the current command.
- f skips the current command in this execution of the keyboard macro, but doesn’t delete it from the macro.
- TAB executes the current command, as well as all similar commands immediately following the current command; for example, TAB may be used to insert a sequence of characters (corresponding to a sequence of `self-insert-command` commands).
- c continues execution (without further editing) until the end of the keyboard macro.  If execution terminates normally, the edited macro replaces the original keyboard macro.
- C-k skips and deletes the rest of the keyboard macro, terminates step-editing, and replaces the original keyboard macro with the edited macro.
- q and C-g cancels the step-editing of the keyboard macro; discarding any changes made to the keyboard macro.
- i key… C-j reads and executes a series of key sequences (not including the final C-j), and inserts them before the current command in the keyboard macro, without advancing over the current command.
- I key… reads one key sequence, executes it, and inserts it before the current command in the keyboard macro, without advancing over the current command.
- r key… C-j reads and executes a series of key sequences (not including the final C-j), and replaces the current command in the keyboard macro with them, advancing over the inserted key sequences.
- R key… reads one key sequence, executes it, and replaces the current command in the keyboard macro with that key sequence, advancing over the inserted key sequence.
- a key… C-j executes the current command, then reads and executes a series of key sequences (not including the final C-j), and inserts them after the current command in the keyboard macro; it then advances over the current command and the inserted key sequences.
- A key… C-j executes the rest of the commands in the keyboard macro, then reads and executes a series of key sequences (not including the final C-j), and appends them at the end of the keyboard macro; it then terminates the step-editing and replaces the original keyboard macro with the edited macro.



### 19.8 Listing and Editing Keyboard Macros



To display a list of existing keyboard macros, type M-x list-keyboard-macros RET.  This pops up the *Kmacro Menu* in a buffer named *Keyboard Macro List*.  Each line in the list shows one macro’s position, counter value, counter format, that counter value using that format, and macro keys.  Here is an example of a macro list:

```
Position  Counter  Format  Formatted  Keys
0               8  %02d    08         N : SPC <F3> RET
1               0  %d      0          l o n g SPC p h r a s e
```

The macros are listed with the current macro at the top in position number zero and the older macros in the order in which they are found in the keyboard macro ring (see [The Keyboard Macro Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macro-Ring.html)).  Using the Kmacro Menu, you can change the order of the macros and change their counters, counter formats, and keys.  The Kmacro Menu is a read-only buffer, and can be changed only through the special commands described in this section.  After a command is run, the Kmacro Menu displays changes to reflect the new values of the macro properties and the macro ring.  You can use the usual cursor motion commands in this buffer, as well as special motion commands for navigating the table.  To view a list of the special commands, type C-h m or ? (`describe-mode`) in the Kmacro Menu.

You can use the following commands to change a macro’s properties:

- #[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dedit_002dposition)

  Change the position of the macro on the current line (see [The Keyboard Macro Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macro-Ring.html)).

- C-x C-t[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dtranspose)

  Move the macro on the current line to the line above, like in `transpose-lines`.

- c[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dedit_002dcounter)

  Change the counter value of the macro on the current line (see [The Keyboard Macro Counter](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keyboard-Macro-Counter.html)).

- f[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dedit_002dformat)

  Change the counter format of the macro on the current line.

- e[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dedit_002dkeys)

  Change the keys of the macro on the current line using `edit-kbd-macro` (see [Editing a Keyboard Macro](https://www.gnu.org/software/emacs/manual/html_node/emacs/Edit-Keyboard-Macro.html)).

- RET[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dedit_002dcolumn)

  Change the value in the current column of the macro on the current line using commands above.

The following commands delete or duplicate macros in the list:

- d[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dflag_002dfor_002ddeletion)

- d (Kmacro Menu)

  Flag the macro on the current line for deletion, then move point to the next line (`kmacro-menu-flag-for-deletion`).  The deletion flag is indicated by the character ‘D’ at the start of line.  The deletion occurs only when you type the x command (see below). If the region is active, this command flags all of the macros in the region.

- x[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002ddo_002dflagged_002ddelete)

- x (Kmacro Menu)

  Delete the macros in the list that have been flagged for deletion (`kmacro-menu-do-flagged-delete`).

- m[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dmark)

- m (Kmacro Menu)

  Mark the macro on the current line, then move point to the next line (`kmacro-menu-mark`).  Marked macros are indicated by the character ‘*’ at the start of line.  Marked macros can be operated on by the C and D commands (see below). If the region is active, this command marks all of the macros in the region.

- C[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002ddo_002dcopy)

- C (Kmacro Menu)

  This command copies macros by duplicating them at their current positions in the list (`kmacro-menu-do-copy`).  For example, running this command on the macro at position number zero will insert a copy of that macro into position number one and move the remaining macros down. If the region is active, this command duplicates the macros in the region.  Otherwise, if there are marked macros, this command duplicates the marked macros.  If there is no region nor are there marked macros, this command duplicates the macro on the current line.  In the first two cases, the command prompts for confirmation before duplication.

- D[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002ddo_002ddelete)

- D (Kmacro Menu)

  This command deletes macros, removing them from the ring (`kmacro-menu-do-delete`).  For example, running this command on the macro at position number zero will delete the current macro and then make the first macro in the macro ring (previously at position number one) the new current macro, popping it from the ring. If the region is active, this command deletes the macros in the region.  Otherwise, if there are marked macros, this command deletes the marked macros.  If there is no region nor are there marked macros, this command deletes the macro on the current line.  In all cases, the command prompts for confirmation before deletion. This command is an alternative to the d and x commands (see above).

- u[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dunmark)

- u (Kmacro Menu)

  Unmark and unflag the macro on the current line, then move point down to the next line (`kmacro-menu-unmark`).  If there is an active region, this command unmarks and unflags all of the macros in the region.

- DEL[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dunmark_002dbackward)

- DEL (Kmacro Menu)

  Like the u command (see above), but move point up to the previous line when there is no active region (`kmacro-menu-unmark-backward`).

- U[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kmacro-Menu.html#index-kmacro_002dmenu_002dunmark_002dall)

- U (Kmacro Menu)

  Unmark and unflag all macros in the list (`kmacro-menu-unmark-all`).



## 20 File Handling



The operating system stores data permanently in named *files*, so most of the text you edit with Emacs comes from a file and is ultimately stored in a file.

To edit a file, you must tell Emacs to read the file and prepare a buffer containing a copy of the file’s text.  This is called *visiting* the file.  Editing commands apply directly to text in the buffer; that is, to the copy inside Emacs.  Your changes appear in the file itself only when you *save* the buffer back into the file.

In addition to visiting and saving files, Emacs can delete, copy, rename, and append to files, keep multiple versions of them, and operate on file directories.



### 20.1 File Names



Many Emacs commands that operate on a file require you to specify the file name, using the minibuffer (see [Minibuffers for File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-File.html)).

While in the minibuffer, you can use the usual completion and history commands (see [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html)).  Note that file name completion ignores file names whose extensions appear in the variable `completion-ignored-extensions` (see [Completion Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Options.html)). Note also that most commands use permissive completion with confirmation for reading file names: you are allowed to submit a nonexistent file name, but if you type RET immediately after completing up to a nonexistent file name, Emacs prints ‘[Confirm]’ and you must type a second RET to confirm. See [Completion Exit](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Exit.html), for details.

Minibuffer history commands offer some special features for reading file names, see [Minibuffer History](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-History.html).



Each buffer has a *default directory*, stored in the buffer-local variable `default-directory`.  Whenever Emacs reads a file name using the minibuffer, it usually inserts the default directory into the minibuffer as the initial contents.  You can inhibit this insertion by changing the variable `insert-default-directory` to `nil` (see [Minibuffers for File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-File.html)).  Regardless, Emacs always assumes that any relative file name is relative to the default directory, e.g., entering a file name without a directory specifies a file in the default directory.



When you visit a file, Emacs sets `default-directory` in the visiting buffer to the directory of its file.  When you create a new buffer that is not visiting a file, via a command like C-x b, its default directory is usually copied from the buffer that was current at the time (see [Creating and Selecting Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Buffer.html)).  You can use the command M-x pwd to see the value of `default-directory` in the current buffer.  The command M-x cd prompts for a directory’s name, and sets the buffer’s `default-directory` to that directory (doing this does not change the buffer’s file name, if any).

As an example, when you visit the file /u/rms/gnu/gnu.tasks, the default directory is set to /u/rms/gnu/.  If you invoke a command that reads a file name, entering just ‘foo’ in the minibuffer, with a directory omitted, specifies the file /u/rms/gnu/foo; entering ‘../.login’ specifies /u/rms/.login; and entering ‘new/foo’ specifies /u/rms/gnu/new/foo.

When typing a file name into the minibuffer, you can make use of a couple of shortcuts: a double slash ignores everything before the second slash in the pair, and ‘~/’ is your home directory. See [Minibuffers for File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-File.html).



The character ‘$’ is used to substitute an environment variable into a file name.  The name of the environment variable consists of all the alphanumeric characters after the ‘$’; alternatively, it can be enclosed in braces after the ‘$’.  For example, if you have used the shell command `export FOO=rms/hacks` to set up an environment variable named `FOO`, then both /u/$FOO/test.c and /u/${FOO}/test.c are abbreviations for /u/rms/hacks/test.c.  If the environment variable is not defined, no substitution occurs, so that the character ‘$’ stands for itself.  Note that environment variables set outside Emacs affect Emacs only if they are applied before Emacs is started.

To access a file with ‘$’ in its name, if the ‘$’ causes expansion, type ‘$$’.  This pair is converted to a single ‘$’ at the same time that variable substitution is performed for a single ‘$’.  Alternatively, quote the whole file name with ‘/:’ (see [Quoted File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quoted-File-Names.html)).  File names which begin with a literal ‘~’ should also be quoted with ‘/:’.

You can include non-ASCII characters in file names. See [Coding Systems for File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Name-Coding.html).



### 20.2 Visiting Files



- C-x C-f

  Visit a file (`find-file`).

- C-x C-r

  Visit a file for viewing, without allowing changes to it (`find-file-read-only`).

- C-x C-v

  Visit a different file instead of the one visited last (`find-alternate-file`).

- C-x 4 f

  Visit a file, in another window (`find-file-other-window`).  Don’t alter what is displayed in the selected window.

- C-x 5 f

  Visit a file, in a new frame (`find-file-other-frame`).  Don’t alter what is displayed in the selected frame.

- M-x find-file-literally

  Visit a file with no conversion of the contents.



*Visiting* a file means reading its contents into an Emacs buffer so you can edit them.  Emacs makes a new buffer for each file that you visit.



To visit a file, type C-x C-f (`find-file`) and use the minibuffer to enter the name of the desired file.  While in the minibuffer, you can abort the command by typing C-g.  See [File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Names.html), for details about entering file names into minibuffers.

If the specified file exists but the system does not allow you to read it, an error message is displayed in the echo area (on GNU and Unix systems you might be able to visit such a file using the ‘su’ or ‘sudo’ methods; see [Remote Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Remote-Files.html)).  Otherwise, you can tell that C-x C-f has completed successfully by the appearance of new text on the screen, and by the buffer name shown in the mode line (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)).  Emacs normally constructs the buffer name from the file name, omitting the directory name.  For example, a file named /usr/rms/emacs.tex is visited in a buffer named ‘emacs.tex’.  If there is already a buffer with that name, Emacs constructs a unique name; the normal method is to add a suffix based on the directory name (e.g., ‘<rms>’, ‘<tmp>’, and so on), but you can select other methods.  See [Making Buffer Names Unique](https://www.gnu.org/software/emacs/manual/html_node/emacs/Uniquify.html).



To create a new file, just visit it using the same command, C-x C-f.  Emacs displays ‘(New file)’ in the echo area, but in other respects behaves as if you had visited an existing empty file.



After visiting a file, the changes you make with editing commands are made in the Emacs buffer.  They do not take effect in the visited file, until you *save* the buffer (see [Saving Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Saving.html)).  If a buffer contains changes that have not been saved, we say the buffer is *modified*.  This implies that some changes will be lost if the buffer is not saved.  The mode line displays two stars near the left margin to indicate that the buffer is modified.

If you visit a file that is already in Emacs, C-x C-f switches to the existing buffer instead of making another copy.  Before doing so, it checks whether the file has changed since you last visited or saved it.  If the file has changed, Emacs offers to reread it.



If you try to visit a file larger than `large-file-warning-threshold` (the default is 10000000, which is about 10 megabytes), Emacs asks you for confirmation first.  You can answer y to proceed with visiting the file or l to visit the file literally (see below).  Visiting large files literally speeds up navigation and editing of such files, because various potentially-expensive features are turned off.  Note, however, that Emacs cannot visit files that are larger than the maximum Emacs buffer size, which is limited by the amount of memory Emacs can allocate and by the integers that Emacs can represent (see [Using Multiple Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Buffers.html)).  If you try, Emacs displays an error message saying that the maximum buffer size has been exceeded.



If you try to visit a file whose major mode (see [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html)) uses the tree-sitter parsing library, Emacs will display a warning if the file’s size in bytes is larger than the value of the variable `treesit-max-buffer-size`.  The default value is 40 megabytes for 64-bit Emacs and 15 megabytes for 32-bit Emacs.  This avoids the danger of having Emacs run out of memory by preventing the activation of major modes based on tree-sitter in such large buffers, because a typical tree-sitter parser needs about 10 times as much memory as the text it parses.



If the file name you specify contains shell-style wildcard characters, Emacs visits all the files that match it.  (On case-insensitive filesystems, Emacs matches the wildcards disregarding the letter case.)  Wildcards include ‘?’, ‘*’, and ‘[…]’ sequences.  To enter the wild card ‘?’ in a file name in the minibuffer, you need to type C-q ?.  See [Quoted File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quoted-File-Names.html), for information on how to visit a file whose name actually contains wildcard characters.  You can disable the wildcard feature by customizing `find-file-wildcards`.



If you’re asking to visit a file that’s already visited in a buffer, but the file has changed externally, Emacs normally asks you whether you want to re-read the file from disk.  But if you set `query-about-changed-file` to `nil`, Emacs won’t query you, but will instead just display the buffer’s contents before the changes, and show an echo-area message telling you how to revert the buffer from the file.



If you visit the wrong file unintentionally by typing its name incorrectly, type C-x C-v (`find-alternate-file`) to visit the file you really wanted.  C-x C-v is similar to C-x C-f, but it kills the current buffer (after first offering to save it if it is modified).  When C-x C-v reads the file name to visit, it inserts the entire default file name in the buffer, with point just after the directory part; this is convenient if you made a slight error in typing the name.



If you visit a file that is actually a directory, Emacs invokes Dired, the Emacs directory browser.  See [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html).  You can disable this behavior by setting the variable `find-file-run-dired` to `nil`; in that case, it is an error to try to visit a directory.

Files which are actually collections of other files, or *file archives*, are visited in special modes which invoke a Dired-like environment to allow operations on archive members.  See [File Archives](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Archives.html), for more about these features.

If you visit a file that the operating system won’t let you modify, or that is marked read-only, Emacs makes the buffer read-only too, so that you won’t go ahead and make changes that you’ll have trouble saving afterward.  You can make the buffer writable with C-x C-q (`read-only-mode`).  See [Miscellaneous Buffer Operations](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Buffer.html).



If you want to visit a file as read-only in order to protect yourself from entering changes accidentally, visit it with the command C-x C-r (`find-file-read-only`) instead of C-x C-f.



C-x 4 f (`find-file-other-window`) is like C-x C-f except that the buffer containing the specified file is selected in another window.  The window that was selected before C-x 4 f continues to show the same buffer it was already showing.  If this command is used when only one window is being displayed, that window is split in two, with one window showing the same buffer as before, and the other one showing the newly requested file.  See [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html).



C-x 5 f (`find-file-other-frame`) is similar, but opens a new frame, or selects any existing frame showing the specified file. See [Frames and Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frames.html).



On graphical displays, there are two additional methods for visiting files.  Firstly, when Emacs is built with a suitable GUI toolkit, commands invoked with the mouse (by clicking on the menu bar or tool bar) use the toolkit’s standard file selection dialog instead of prompting for the file name in the minibuffer.  On GNU/Linux and Unix platforms, Emacs does this when built with GTK+, LessTif, and Motif toolkits; on MS-Windows and Mac, the GUI version does that by default. For information on how to customize this, see [Using Dialog Boxes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dialog-Boxes.html).

Secondly, Emacs supports drag and drop: dropping a file into an ordinary Emacs window visits the file using that window.  As an exception, dropping a file into a window displaying a Dired buffer moves or copies the file into the displayed directory.  For details, see [Drag and Drop](https://www.gnu.org/software/emacs/manual/html_node/emacs/Drag-and-Drop.html), and [Other Dired Features](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Dired-Features.html).

On text-mode terminals and on graphical displays when Emacs was built without a GUI toolkit, you can visit files via the menu-bar ‘File’ menu, which has the ‘Visit New File’ and the ‘Open File’ items.

Each time you visit a file, Emacs automatically scans its contents to detect what character encoding and end-of-line convention it uses, and converts these to Emacs’s internal encoding and end-of-line convention within the buffer.  When you save the buffer, Emacs performs the inverse conversion, writing the file to disk with its original encoding and end-of-line convention.  See [Coding Systems](https://www.gnu.org/software/emacs/manual/html_node/emacs/Coding-Systems.html).



If you wish to edit a file as a sequence of ASCII characters with no special encoding or conversion, use the M-x find-file-literally command.  This visits a file, like C-x C-f, but does not do format conversion (see [Format Conversion](https://www.gnu.org/software/emacs/manual/html_node/elisp/Format-Conversion.html#Format-Conversion) in the Emacs Lisp Reference Manual), character code conversion (see [Coding Systems](https://www.gnu.org/software/emacs/manual/html_node/emacs/Coding-Systems.html)), or automatic uncompression (see [Accessing Compressed Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compressed-Files.html)), and does not add a final newline because of `require-final-newline` (see [Customizing Saving of Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customize-Save.html)).  If you have already visited the same file in the usual (non-literal) manner, this command asks you whether to visit it literally instead.



Files are sometimes (loosely) tied to other files, and you could call these files *sibling files*.  For instance, when editing C files, if you have a file called ‘"foo.c"’, you often also have a file called ‘"foo.h"’, and that could be its sibling file.  Or you may have different versions of a file, for instance ‘"src/emacs/emacs-27/lisp/allout.el"’ and ‘"src/emacs/emacs-28/lisp/allout.el"’ might be considered siblings.  Emacs provides the `find-sibling-file` command to jump between sibling files, but it’s impossible to guess at which files a user might want to be considered siblings, so Emacs lets you configure this freely by altering the `find-sibling-rules` user option. This is a list of match/expansion elements.

For instance, to do the ‘".c"’ to ‘".h"’ mapping, you could say:

```
(setq find-sibling-rules
      '(("\\([^/]+\\)\\.c\\'" "\\1.h")))
```

(`ff-find-related-file` offers similar functionality especially geared towards C files, see [Other Commands for C Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Other-C-Commands.html).)

Or, if you want to consider all files under ‘"src/emacs/DIR/file-name"’ to be siblings of other dirs, you could say:

```
(setq find-sibling-rules
      '(("src/emacs/[^/]+/\\(.*\\)\\'" "src/emacs/.*/\\1")))
```

As you can see, this is a list of `(MATCH EXPANSION...)` elements.  The match is a regular expression that matches the visited file name, and each expansion may refer to match groups by using ‘\\1’ and so on.  The resulting expansion string is then applied to the file system to see if any files match this expansion (interpreted as a regexp).



Two special hook variables allow extensions to modify the operation of visiting files.  Visiting a file that does not exist runs the functions in `find-file-not-found-functions`; this variable holds a list of functions, which are called one by one (with no arguments) until one of them returns non-`nil`.  This is not a normal hook, and the name ends in ‘-functions’ rather than ‘-hook’ to indicate that fact.

Successful visiting of any file, whether existing or not, calls the functions in `find-file-hook`, with no arguments.  This variable is a normal hook.  In the case of a nonexistent file, the `find-file-not-found-functions` are run first.  See [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html).

There are several ways to specify automatically the major mode for editing the file (see [Choosing File Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Choosing-Modes.html)), and to specify local variables defined for that file (see [Local Variables in Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Variables.html)).



### 20.3 Saving Files

*Saving* a buffer in Emacs means writing its contents back into the file that was visited in the buffer.

#### 20.3.1 Commands for Saving Files

These are the commands that relate to saving and writing files.

- C-x C-s

  Save the current buffer to its file (`save-buffer`).

- C-x s

  Save any or all buffers to their files (`save-some-buffers`).

- M-~

  Forget that the current buffer has been changed (`not-modified`). With prefix argument (C-u), mark the current buffer as changed.

- C-x C-w

  Save the current buffer with a specified file name (`write-file`).

- M-x set-visited-file-name

  Change the file name under which the current buffer will be saved.

- M-x rename-visited-file

  The same as M-x set-visited-file-name, but also rename the file the buffer is visiting (if any).



When you wish to save the file and make your changes permanent, type C-x C-s (`save-buffer`).  After saving is finished, C-x C-s displays a message like this:

```
Wrote /u/rms/gnu/gnu.tasks
```

If the current buffer is not modified (no changes have been made in it since the buffer was created or last saved), saving is not really done, because it would have no effect.  Instead, C-x C-s displays a message like this in the echo area:

```
(No changes need to be saved)
```

With a prefix argument, C-u C-x C-s, Emacs also marks the buffer to be backed up when the next save is done.  See [Backup Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Backup.html).



The command C-x s (`save-some-buffers`) offers to save any or all modified buffers.  It asks you what to do with each buffer.  The possible responses are analogous to those of `query-replace`:

- y

- SPC

  Save this buffer and ask about the rest of the buffers.

- n

- DEL

  Don’t save this buffer, but ask about the rest of the buffers.

- !

  Save this buffer and all the rest with no more questions.

- q

- RET

  Terminate `save-some-buffers` without any more saving.

- .

  Save this buffer, then exit `save-some-buffers` without even asking about other buffers.

- C-r

  View the buffer that you are currently being asked about.  When you exit View mode, you get back to `save-some-buffers`, which asks the question again.

- C-f

  Exit `save-some-buffers` and visit the buffer that you are currently being asked about.

- d

  Diff the buffer against its corresponding file, so you can see what changes you would be saving.  This calls the command `diff-buffer-with-file` (see [Comparing Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Comparing-Files.html)).

- C-h

  Display a help message about these options.



You can customize the value of `save-some-buffers-default-predicate` to control which buffers Emacs will ask about.

C-x C-c, the key sequence to exit Emacs, invokes `save-some-buffers` and therefore asks the same questions.



If you have changed a buffer but do not wish to save the changes, you should take some action to prevent it.  Otherwise, each time you use C-x s or C-x C-c, you are liable to save this buffer by mistake.  One thing you can do is type M-~ (`not-modified`), which clears out the indication that the buffer is modified.  If you do this, none of the save commands will believe that the buffer needs to be saved.  (‘~’ is often used as a mathematical symbol for “not”; thus M-~ is “not”, metafied.) Alternatively, you can cancel all the changes made since the file was visited or saved, by reading the text from the file again.  This is called *reverting*.  See [Reverting a Buffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Reverting.html).  (You could also undo all the changes by repeating the undo command C-x u until you have undone all the changes; but reverting is easier.)



M-x set-visited-file-name alters the name of the file that the current buffer is visiting.  It reads the new file name using the minibuffer.  Then it marks the buffer as visiting that file name, and changes the buffer name correspondingly.  `set-visited-file-name` does not save the buffer in the newly visited file; it just alters the records inside Emacs in case you do save later.  It also marks the buffer as modified so that C-x C-s in that buffer *will* save.



If you wish to mark the buffer as visiting a different file and save it right away, use C-x C-w (`write-file`).  This is equivalent to `set-visited-file-name` followed by C-x C-s, except that C-x C-w asks for confirmation if the file exists. C-x C-s used on a buffer that is not visiting a file has the same effect as C-x C-w; that is, it reads a file name, marks the buffer as visiting that file, and saves it there.  The default file name in a buffer that is not visiting a file is made by combining the buffer name with the buffer’s default directory (see [File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Names.html)).

If the new file name implies a major mode, then C-x C-w switches to that major mode, in most cases.  The command `set-visited-file-name` also does this.  See [Choosing File Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Choosing-Modes.html).

If you wish to save the current buffer to a different file without visiting that file, use `mark-whole-buffer` (C-x h), then M-x write-region (see [Miscellaneous File Operations](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-File-Ops.html)).

If Emacs is about to save a file and sees that the date of the latest version on disk does not match what Emacs last read or wrote, Emacs notifies you of this fact, because it probably indicates a problem caused by simultaneous editing and requires your immediate attention. See [Simultaneous Editing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Interlocking.html).



#### 20.3.2 Backup Files



On most operating systems, rewriting a file automatically destroys all record of what the file used to contain.  Thus, saving a file from Emacs throws away the old contents of the file—or it would, except that Emacs carefully copies the old contents to another file, called the *backup* file, before actually saving.

Emacs makes a backup for a file only the first time the file is saved from the buffer that visits it.  No matter how many times you subsequently save the file, its backup remains unchanged.  However, if you kill the buffer and then visit the file again, a new backup file will be made.

For most files, the variable `make-backup-files` determines whether to make backup files.  On most operating systems, its default value is `t`, so that Emacs does write backup files.

For files managed by a version control system (see [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html)), the variable `vc-make-backup-files` determines whether to make backup files.  By default it is `nil`, since backup files are redundant when you store all the previous versions in a version control system. See [General Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/General-VC-Options.html).

At your option, Emacs can keep either a single backup for each file, or make a series of numbered backup files for each file that you edit. See [Single or Numbered Backups](https://www.gnu.org/software/emacs/manual/html_node/emacs/Backup-Names.html).



The default value of the `backup-enable-predicate` variable prevents backup files being written for files in the directories used for temporary files, specified by `temporary-file-directory` or `small-temporary-file-directory`.

You can explicitly tell Emacs to make another backup file from a buffer, even though that buffer has been saved before.  If you save the buffer with C-u C-x C-s, the version thus saved will be made into a backup file if you save the buffer again.  C-u C-u C-x C-s saves the buffer, but first makes the previous file contents into a new backup file.  C-u C-u C-u C-x C-s does both things: it makes a backup from the previous contents, and arranges to make another from the newly saved contents if you save again.



You can customize the variable `backup-directory-alist` to specify that files matching certain patterns should be backed up in specific directories.  A typical use is to add an element `("." . dir)` to make all backups in the directory with absolute name dir.  Emacs modifies the backup file names to avoid clashes between files with the same names originating in different directories.  Alternatively, adding, `("." . ".~")` would make backups in the invisible subdirectory .~ of the original file’s directory.  Emacs creates the directory, if necessary, to make the backup.



#### 20.3.2.1 Single or Numbered Backups



When Emacs makes a backup file, its name is normally constructed by appending ‘~’ to the file name being edited; thus, the backup file for eval.c would be eval.c~.



If access control stops Emacs from writing backup files under the usual names, it writes the backup file as ~/.emacs.d/%backup%~. Only one such file can exist, so only the most recently made such backup is available.

Emacs can also make *numbered backup files*.  Numbered backup file names contain ‘.~’, the number, and another ‘~’ after the original file name.  Thus, the backup files of eval.c would be called eval.c.~1~, eval.c.~2~, and so on, all the way through names like eval.c.~259~ and beyond.



The variable `version-control` determines whether to make single backup files or multiple numbered backup files.  Its possible values are:

- `nil`

  Make numbered backups for files that have numbered backups already. Otherwise, make single backups.  This is the default.

- `t`

  Make numbered backups.

- `never`

  Never make numbered backups; always make single backups.

The usual way to set this variable is globally, through your init file or the customization buffer.  However, you can set `version-control` locally in an individual buffer to control the making of backups for that buffer’s file (see [Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Locals.html)).  Some modes, such as Rmail mode, set this variable.  You can also have Emacs set `version-control` locally whenever you visit a given file (see [Local Variables in Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Variables.html)).



If you set the environment variable `VERSION_CONTROL`, to tell various GNU utilities what to do with backup files, Emacs also obeys the environment variable by setting the Lisp variable `version-control` accordingly at startup.  If the environment variable’s value is ‘t’ or ‘numbered’, then `version-control` becomes `t`; if the value is ‘nil’ or ‘existing’, then `version-control` becomes `nil`; if it is ‘never’ or ‘simple’, then `version-control` becomes `never`.



If you set the variable `make-backup-file-name-function` to a suitable Lisp function, you can override the usual way Emacs constructs backup file names.



#### 20.3.2.2 Automatic Deletion of Backups

To prevent excessive consumption of disk space, Emacs can delete numbered backup versions automatically.  Generally Emacs keeps the first few backups and the latest few backups, deleting any in between.  This happens every time a new backup is made.



The two variables `kept-old-versions` and `kept-new-versions` control this deletion.  Their values are, respectively, the number of oldest (lowest-numbered) backups to keep and the number of newest (highest-numbered) ones to keep, each time a new backup is made.  The backups in the middle (excluding those oldest and newest) are the excess middle versions—those backups are deleted.  These variables’ values are used when it is time to delete excess versions, just after a new backup version is made; the newly made backup is included in the count in `kept-new-versions`.  By default, both variables are 2.



If `delete-old-versions` is `t`, Emacs deletes the excess backup files silently.  If it is `nil`, the default, Emacs asks you whether it should delete the excess backup versions.  If it has any other value, then Emacs never automatically deletes backups.

Dired’s . (Period) command can also be used to delete old versions. See [Flagging Many Files at Once](https://www.gnu.org/software/emacs/manual/html_node/emacs/Flagging-Many-Files.html).



#### 20.3.2.3 Copying vs. Renaming

Backup files can be made by copying the old file or by renaming it. This makes a difference when the old file has multiple names (hard links).  If the old file is renamed into the backup file, then the alternate names become names for the backup file.  If the old file is copied instead, then the alternate names remain names for the file that you are editing, and the contents accessed by those names will be the new contents.

The method of making a backup file may also affect the file’s owner and group.  If copying is used, these do not change.  If renaming is used, you become the file’s owner, and the file’s group becomes the default (different operating systems have different defaults for the group).



The choice of renaming or copying is made as follows:

- If the variable `backup-by-copying` is non-`nil` (the default is `nil`), use copying.

- Otherwise, if the variable `backup-by-copying-when-linked` is non-`nil` (the default is `nil`), and the file has multiple names, use copying.

- Otherwise, if the variable 

  ```
  backup-by-copying-when-mismatch
  ```

   is non-

  ```
  nil
  ```

   (the default is 

  ```
  t
  ```

  ), and renaming would change the file’s owner or group, use copying.

  If you change `backup-by-copying-when-mismatch` to `nil`, Emacs checks the numeric user-id of the file’s owner and the numeric group-id of the file’s group.  If either is no greater than `backup-by-copying-when-privileged-mismatch`, then it behaves as though `backup-by-copying-when-mismatch` is non-`nil` anyway.

- Otherwise, renaming is the default choice.

When a file is managed with a version control system (see [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html)), Emacs does not normally make backups in the usual way for that file.  But *committing* (a.k.a. *checking in*, see [Concepts of Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/VCS-Concepts.html)) new versions of files is similar in some ways to making backups.  One unfortunate similarity is that these operations typically break hard links, disconnecting the file name you visited from any alternate names for the same file.  This has nothing to do with Emacs—the version control system does it.

Some file storage services support *file versioning*: they record history of previous versions of files, and allow reverting to those previous versions.  If you want to be able to do that with files hosted by those services when editing them with Emacs, customize `backup-by-copying` to a non-`nil` value.



Copying the old file for backup is also useful when editing precious files, because it makes sure the old file keeps its name if something fails between the backup and the saving of your edits.  Alternatively, you can customize `file-precious-flag` to a non-`nil` value, which implies backups by copying and also protects against I/O errors while saving your edits.



#### 20.3.3 Customizing Saving of Files



If the value of the variable `require-final-newline` is `t`, saving or writing a file silently puts a newline at the end if there isn’t already one there.  If the value is `visit`, Emacs adds a newline at the end of any file that doesn’t have one, just after it visits the file.  (This marks the buffer as modified, and you can undo it.)  If the value is `visit-save`, Emacs adds such newlines both on visiting and on saving.  If the value is `nil`, Emacs leaves the end of the file unchanged; any other non-`nil` value means Emacs asks you whether to add a newline.  The default is `nil`.



Some major modes are designed for specific kinds of files that are always supposed to end in newlines.  Such major modes set the variable `require-final-newline` to the value of `mode-require-final-newline`, which defaults to `t`.  By setting the latter variable, you can control how these modes handle final newlines.



If this option is non-`nil` and you’re visiting a file via a symbolic link, Emacs will break the symbolic link upon saving the buffer, and will write the buffer to a file with the same name as the symbolic link, if the value of `file-precious-flag` is non-`nil` (see [file-precious-flag](https://www.gnu.org/software/emacs/manual/html_node/elisp/Saving-Buffers.html#Saving-Buffers) in The Emacs Lisp Reference Manual).  If you want Emacs to save the buffer to the file the symbolic link points to (thereby preserving the link) in these cases, customize the variable `file-preserve-symlinks-on-save` to `t`.



Normally, when a program writes a file, the operating system briefly caches the file’s data in main memory before committing the data to secondary storage.  Although this can greatly improve performance, it risks data loss if the system loses power before committing the cache, and on some platforms other processes might not immediately notice the file’s change.

To lessen this risk, Emacs can invoke the `fsync` system call after saving a file.  Using `fsync` does not eliminate the risk of data loss or slow notification, partly because many systems do not support `fsync` properly, and partly because Emacs’s file-saving procedure typically relies also on directory updates that might not survive a crash even if `fsync` works properly.

The `write-region-inhibit-fsync` variable controls whether Emacs invokes `fsync` after saving a file.  The variable’s default value is `t`.

Emacs never uses `fsync` when writing auto-save files, as these files might lose data anyway.



#### 20.3.4 Protection against Simultaneous Editing



Simultaneous editing occurs when two users visit the same file, both make changes, and then both save them.  If nobody is informed that this is happening, whichever user saves first would later find that their changes were lost.

On some systems, Emacs notices immediately when the second user starts to change the file, and issues an immediate warning.  On all systems, Emacs checks when you save the file, and warns if you are about to overwrite another user’s changes.  You can prevent loss of the other user’s work by taking the proper corrective action instead of saving the file.



When you make the first modification in an Emacs buffer that is visiting a file, Emacs records that the file is *locked* by you. (It does this by creating a specially-named symbolic link[7](https://www.gnu.org/software/emacs/manual/html_node/emacs/Interlocking.html#FOOT7) with special contents in the same directory.  See [(elisp)File Locks](https://www.gnu.org/software/emacs/manual/html_node/elisp/File-Locks.html#File-Locks), for more details.)  Emacs removes the lock when you save the changes.  The idea is that the file is locked whenever an Emacs buffer visiting it has unsaved changes.



You can prevent the creation of lock files by setting the variable `create-lockfiles` to `nil`.  **Caution:** by doing so you will lose the benefits that this feature provides.  You can also control where lock files are written by using the `lock-file-name-transforms` variable.



If you begin to modify the buffer while the visited file is locked by someone else, this constitutes a *collision*.  When Emacs detects a collision, it asks you what to do, by calling the Lisp function `ask-user-about-lock`.  You can redefine this function for the sake of customization.  The standard definition of this function asks you a question and accepts three possible answers:

- s

  Steal the lock.  Whoever was already changing the file loses the lock, and you gain the lock.

- p

  Proceed.  Go ahead and edit the file despite its being locked by someone else.

- q

  Quit.  This causes an error (`file-locked`), and the buffer contents remain unchanged—the modification you were trying to make does not actually take place.

If Emacs or the operating system crashes, this may leave behind lock files which are stale, so you may occasionally get warnings about spurious collisions.  When you determine that the collision is spurious, just use p to tell Emacs to go ahead anyway.

Note that locking works on the basis of a file name; if a file has multiple names, Emacs does not prevent two users from editing it simultaneously under different names.

A lock file cannot be written in some circumstances, e.g., if Emacs lacks the system permissions or cannot create lock files for some other reason.  In these cases, Emacs can still detect the collision when you try to save a file, by checking the file’s last-modification date.  If the file has changed since the last time Emacs visited or saved it, that implies that changes have been made in some other way, and will be lost if Emacs proceeds with saving.  Emacs then displays a warning message and asks for confirmation before saving; answer yes to save, and no or C-g cancel the save.

If you are notified that simultaneous editing has already taken place, one way to compare the buffer to its file is the M-x diff-buffer-with-file command.  See [Comparing Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Comparing-Files.html).



You can prevent the creation of remote lock files by setting the variable `remote-file-name-inhibit-locks` to `t`.



The minor mode `lock-file-mode`, called interactively, toggles the local value of `create-lockfiles` in the current buffer.



#### 20.3.5 Shadowing Files



You can arrange to keep identical *shadow* copies of certain files in more than one place—possibly on different machines.  To do this, first you must set up a *shadow file group*, which is a set of identically-named files shared between a list of sites.  The file group is permanent and applies to further Emacs sessions as well as the current one.  Once the group is set up, every time you exit Emacs, it will copy the file you edited to the other files in its group.  You can also do the copying without exiting Emacs, by typing M-x shadow-copy-files.



A *shadow cluster* is a group of hosts that share directories, so that copying to or from one of them is sufficient to update the file on all of them.  Each shadow cluster has a name, and specifies the network address of a primary host (the one we copy files to), and a regular expression that matches the host names of all the other hosts in the cluster.  You can define a shadow cluster with M-x shadow-define-cluster.

- M-x shadow-initialize

  Set up file shadowing.

- M-x shadow-define-literal-group

  Declare a single file to be shared between sites.

- M-x shadow-define-regexp-group

  Make all files that match each of a group of files be shared between hosts.

- M-x shadow-define-cluster RET name RET

  Define a shadow file cluster name.

- M-x shadow-copy-files

  Copy all pending shadow files.

- M-x shadow-cancel

  Cancel the instruction to shadow some files.

To set up a shadow file group, use M-x shadow-define-literal-group or M-x shadow-define-regexp-group.  See their documentation strings for further information.

Before copying a file to its shadows, Emacs asks for confirmation. You can answer “no” to bypass copying of this file, this time.  If you want to cancel the shadowing permanently for a certain file, use M-x shadow-cancel to eliminate or change the shadow file group.

File Shadowing is not available on MS Windows.



#### 20.3.6 Updating Time Stamps Automatically



You can arrange to have a time stamp in a file be updated automatically each time you save the file. (A time stamp may also be called a date stamp or a last modified time.) Having a time stamp in the text of a file ensures that the time the file was written will be preserved even if the file is copied or transformed in a way that loses the file system’s modification time.

There are two steps to setting up automatic time stamping. First, the file needs a time stamp template somewhere in the first eight lines. The template looks like this:

```
Time-stamp: <>
```

or (your choice) like this:

```
Time-stamp: " "
```



With that template in place, you can update the current buffer’s time stamp once immediately with the command M-x time-stamp. Emacs will check for a template; if a template is found, Emacs will write the current date, time, author, and/or other info between the brackets or quotes. (If the buffer has no template, `time-stamp` does nothing.) After the first time stamp, the line might look like this:

```
Time-stamp: <1993-07-06 11:05:14 terryg>
```

Second, configure your Emacs to run `time-stamp` whenever it saves a file, by adding `time-stamp` to `before-save-hook` (see [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html)). You can either use M-x customize-option (see [Customizing Specific Items](https://www.gnu.org/software/emacs/manual/html_node/emacs/Specific-Customization.html)) to customize the option `before-save-hook`, or you can edit your init file adding this line:

```
(add-hook 'before-save-hook 'time-stamp)
```



#### 20.3.6.1 Customizing the Time Stamp



To customize the time stamp in a particular file, set the variable `time-stamp-pattern` in that file’s local variables list (see [Specifying File Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Specifying-File-Variables.html)). You can change what pattern `time-stamp` will match against to identify a template and where in the file to look for the pattern using `time-stamp-pattern`; for details, see the variable’s built-in documentation (with C-h v, see [Help by Command or Variable Name](https://www.gnu.org/software/emacs/manual/html_node/emacs/Name-Help.html)).

As a simple example, if this line occurs near the top of a file:

```
publishing_year_and_city = "Published nnnn in Boston, Mass.";
```

then the following comment at the end of the same file tells `time-stamp` how to identify and update that custom template:

```
// Local variables:
// time-stamp-pattern: "Published %Y in Boston"
// End:
```

This pattern says that the text before the start of the time stamp is “Published ”, and the text after the end is “ in Boston”. If `time-stamp` finds both in one of the first eight lines, what is between will be replaced by the current year, as requested by the `%Y` format.

After any change to file-local variables, type M-x normal-mode to re-read them.

Here is another example, with the time stamp inserted into the last paragraph of an HTML document. Since this template is at the end of the document, not in the first eight lines, `time-stamp-pattern` starts with `-10/` to tell `time-stamp` to look at the last 10 lines. The `%%` asks for the default format (specified by `time-stamp-format`).

```
…
<p>Last modified: </p>
</body>
</html>
<!--
Local variables:
time-stamp-pattern: "-10/Last modified: %%</p>$"
End:
-->
```

By default the time stamp is formatted according to your locale setting (see [Environment Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Environment.html)) and time zone (see [Time of Day](https://www.gnu.org/software/emacs/manual/html_node/elisp/Time-of-Day.html#Time-of-Day) in The Emacs Lisp Reference Manual). Set `time-stamp-time-zone` to override the time zone used. See the built-in documentation for the variable `time-stamp-format` for specifics on formatting and other variables that affect it.



#### 20.3.6.2 Forcing Time Stamps for One File

If you are working on a file with multiple authors, and you cannot be sure the other authors have enabled time-stamping globally in their Emacs init files, you can force it to be enabled for a particular file by adding `time-stamp` to that buffer’s `before-save-hook` in that file’s local variables list. To extend one of the previous examples:

```
// Local variables:
// eval: (add-hook 'before-save-hook 'time-stamp nil t)
// time-stamp-pattern: "Published %Y in Boston"
// End:
```

Although this example shows them both set together, you can use `eval` without also setting `time-stamp-pattern` if you like the default pattern.



### 20.4 Reverting a Buffer



If you have made extensive changes to a file-visiting buffer and then change your mind, you can *revert* the changes and go back to the saved version of the file.  To do this, type C-x x g.  Since reverting unintentionally could lose a lot of work, Emacs asks for confirmation first if the buffer is modified.

The `revert-buffer` command tries to position point in such a way that, if the file was edited only slightly, you will be at approximately the same part of the text as before.  But if you have made major changes, point may end up in a totally different location.

Reverting marks the buffer as not modified.  However, it adds the reverted changes as a single modification to the buffer’s undo history (see [Undo](https://www.gnu.org/software/emacs/manual/html_node/emacs/Undo.html)).  Thus, after reverting, you can type C-/ or its aliases to bring the reverted changes back, if you happen to change your mind.



To revert a buffer more conservatively, you can use the command `revert-buffer-with-fine-grain`.  This command acts like `revert-buffer`, but it tries to be as non-destructive as possible, making an effort to preserve all markers, properties and overlays in the buffer.  Since reverting this way can be very slow when you have made a large number of changes, you can modify the variable `revert-buffer-with-fine-grain-max-seconds` to specify a maximum amount of seconds that replacing the buffer contents this way should take.  Note that it is not ensured that the whole execution of `revert-buffer-with-fine-grain` won’t take longer than this.

Some kinds of buffers that are not associated with files, such as Dired buffers, can also be reverted.  For them, reverting means recalculating their contents.  Buffers created explicitly with C-x b cannot be reverted; `revert-buffer` reports an error if you try.



When you edit a file that changes automatically and frequently—for example, a log of output from a process that continues to run—it may be useful for Emacs to revert the file without querying you.  To request this behavior, set the variable `revert-without-query` to a list of regular expressions.  When a file name matches one of these regular expressions, `find-file` and `revert-buffer` will revert it automatically if it has changed—provided the buffer itself is not modified.  (If you have edited the text, it would be wrong to discard your changes.)



The C-x x g keystroke is bound to the `revert-buffer-quick` command.  This is like the `revert-buffer` command, but prompts less.  Unlike `revert-buffer`, it will not prompt if the current buffer visits a file, and the buffer is not modified.  It also respects the `revert-buffer-quick-short-answers` user option.  If this option is non-`nil`, use a shorter y/n query instead of a longer yes/no query.

You can also tell Emacs to revert buffers automatically when their visited files change on disk; see [Auto Revert: Keeping buffers automatically up-to-date](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Revert.html).



Note that reverting a buffer turns on the major mode appropriate for visiting the buffer’s file, as described in [Choosing File Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Choosing-Modes.html).  Thus, the major mode actually turned on as result of reverting a buffer depends on mode remapping, and could be different from the original mode if you customized `major-mode-remap-alist` in-between.



### 20.5 Auto Revert: Keeping buffers automatically up-to-date



A buffer can get out of sync with respect to its visited file on disk if that file is changed by another program.  To keep it up to date, you can enable Auto Revert mode by typing M-x auto-revert-mode. This automatically reverts the buffer when its visited file changes on disk.  To do the same for all file buffers, type M-x global-auto-revert-mode to enable Global Auto Revert mode.

Auto Revert will not revert a buffer if it has unsaved changes, or if its file on disk is deleted or renamed.

One use of Auto Revert mode is to “tail” a file such as a system log, so that changes made to that file by other programs are continuously displayed.  To do this, just move the point to the end of the buffer, and it will stay there as the file contents change. However, if you are sure that the file will only change by growing at the end, use Auto Revert Tail mode instead (`auto-revert-tail-mode`).  It is more efficient for this. Auto Revert Tail mode also works for remote files.



When a buffer is auto-reverted, a message is generated.  This can be suppressed by setting `auto-revert-verbose` to `nil`.



The Auto Revert modes do not check or revert remote files, because that is usually too slow.  This behavior can be changed by setting the variable `auto-revert-remote-files` to non-`nil`.



By default, Auto Revert mode works using *file notifications*, whereby changes in the filesystem are reported to Emacs by the OS. You can disable use of file notifications by customizing the variable `auto-revert-use-notify` to a `nil` value, then Emacs will check for file changes by polling every five seconds.  You can change the polling interval through the variable `auto-revert-interval`.

Not all systems support file notifications; where they are not supported, `auto-revert-use-notify` will be `nil` by default.



By default, Auto Revert mode will poll files for changes periodically even when file notifications are used.  Polling is unnecessary in many cases, and turning it off may save power by relying on notifications only.  To do so, set the variable `auto-revert-avoid-polling` to non-`nil`.  However, notification is ineffective on certain file systems; mainly network file system on Unix-like machines, where files can be altered from other machines.  For such file systems, polling may be necessary. To force polling when `auto-revert-avoid-polling` is non-`nil`, set `auto-revert-notify-exclude-dir-regexp` to match files that should be excluded from using notification.

In Dired buffers (see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html)), Auto Revert mode refreshes the buffer when a file is created or deleted in the buffer’s directory.

See [Undoing Version Control Actions](https://www.gnu.org/software/emacs/manual/html_node/emacs/VC-Undo.html), for commands to revert to earlier versions of files under version control.  See [Version Control and the Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/VC-Mode-Line.html), for Auto Revert peculiarities when visiting files under version control.



#### 20.5.1 Auto Reverting Non-File Buffers

Global Auto Revert Mode normally only reverts file buffers.  There are two ways to auto-revert certain non-file buffers: by enabling Auto Revert Mode in those buffers (using M-x auto-revert-mode); and by setting `global-auto-revert-non-file-buffers` to a non-`nil` value.  The latter enables Auto Reverting for all types of buffers for which it is implemented (listed in the menu below).

Like file buffers, non-file buffers should normally not revert while you are working on them, or while they contain information that might get lost after reverting.  Therefore, they do not revert if they are modified.  This can get tricky, because deciding when a non-file buffer should be marked modified is usually more difficult than for file buffers.

Another tricky detail is that, for efficiency reasons, Auto Revert often does not try to detect all possible changes in the buffer, only changes that are major or easy to detect.  Hence, enabling auto-reverting for a non-file buffer does not always guarantee that all information in the buffer is up-to-date, and does not necessarily make manual reverts useless.

At the other extreme, certain buffers automatically revert every `auto-revert-interval` seconds.  (This currently only applies to the Buffer Menu.)  In this case, Auto Revert does not print any messages while reverting, even when `auto-revert-verbose` is non-`nil`.



Some non-file buffers can be updated reliably by file notification on their default directory; Dired buffers is an example.  The major mode can indicate this by setting `buffer-auto-revert-by-notification` to a non-`nil` value in that buffer, allowing Auto Revert to avoid periodic polling.  Such notification does not include changes to files in that directory, only to the directory itself.

The details depend on the particular types of buffers and are explained in the corresponding sections.



#### 20.5.1.1 Auto Reverting the Buffer Menu

If auto-reverting of non-file buffers is enabled, the Buffer Menu (see [Operating on Several Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Several-Buffers.html)) automatically reverts every `auto-revert-interval` seconds, whether there is a need for it or not.  (It would probably take longer to check whether there is a need than to actually revert.)

If the Buffer Menu inappropriately gets marked modified, just revert it manually using g and auto-reverting will resume.  However, if you marked certain buffers to get deleted or to be displayed, you have to be careful, because reverting erases all marks.  The fact that adding marks sets the buffer’s modified flag prevents Auto Revert from automatically erasing the marks.



#### 20.5.1.2 Auto Reverting Dired buffers

Dired buffers only auto-revert when the file list of the buffer’s main directory changes (e.g., when a new file is added or deleted).  They do not auto-revert when information about a particular file changes (e.g., when the size changes) or when inserted subdirectories change. To be sure that *all* listed information is up to date, you have to manually revert using g, *even* if auto-reverting is enabled in the Dired buffer.  Sometimes, you might get the impression that modifying or saving files listed in the main directory actually does cause auto-reverting.  This is because making changes to a file, or saving it, very often causes changes in the directory itself; for instance, through backup files or auto-save files.  However, this is not guaranteed.

If the Dired buffer is marked modified and there are no changes you want to protect, then most of the time you can make auto-reverting resume by manually reverting the buffer using g.  There is one exception.  If you flag or mark files, you can safely revert the buffer.  This will not erase the flags or marks (unless the marked file has been deleted, of course).  However, the buffer will stay modified, even after reverting, and auto-reverting will not resume. This is because, if you flag or mark files, you may be working on the buffer and you might not want the buffer to change without warning. If you want auto-reverting to resume in the presence of marks and flags, mark the buffer non-modified using M-~.  However, adding, deleting or changing marks or flags will mark it modified again.

Remote Dired buffers are currently not auto-reverted.  Neither are Dired buffers for which you used shell wildcards or file arguments to list only some of the files.  *Find* and *Locate* buffers do not auto-revert either.

Note that auto-reverting Dired buffers may not work satisfactorily on some systems.



### 20.6 Auto-Saving: Protection Against Disasters



From time to time, Emacs automatically saves each visited file in a separate file, without altering the file you actually use.  This is called *auto-saving*.  It prevents you from losing more than a limited amount of work if the system crashes.



When Emacs determines that it is time for auto-saving, it considers each buffer, and each is auto-saved if auto-saving is enabled for it and it has been changed since the last time it was auto-saved.  When the `auto-save-no-message` variable is set to `nil` (the default), the message ‘Auto-saving...’ is displayed in the echo area during auto-saving, if any files are actually auto-saved; to disable these messages, customize the variable to a non-`nil` value.  Errors occurring during auto-saving are caught so that they do not interfere with the execution of commands you have been typing.



#### 20.6.1 Auto-Save Files

Auto-saving does not normally save in the files that you visited, because it can be very undesirable to save a change that you did not want to make permanent.  Instead, auto-saving is done in a different file called the *auto-save file*, and the visited file is changed only when you request saving explicitly (such as with C-x C-s).



Normally, the auto-save file name is made by appending ‘#’ to the front and rear of the visited file name.  Thus, a buffer visiting file foo.c is auto-saved in a file #foo.c#.  Most buffers that are not visiting files are auto-saved only if you request it explicitly; when they are auto-saved, the auto-save file name is made by appending ‘#’ to the front and rear of buffer name, then adding digits and letters at the end for uniqueness.  For example, the *mail* buffer in which you compose messages to be sent might be auto-saved in a file named #*mail*#704juu.  Auto-save file names are made this way unless you reprogram parts of Emacs to do something different (the functions `make-auto-save-file-name` and `auto-save-file-name-p`).  The file name to be used for auto-saving in a buffer is calculated when auto-saving is turned on in that buffer.



The variable `auto-save-file-name-transforms` allows a degree of control over the auto-save file name.  It lets you specify a series of regular expressions and replacements to transform the auto save file name.  The default value puts the auto-save files for remote files (see [Remote Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Remote-Files.html)) into the temporary file directory on the local machine.

When you delete a substantial part of the text in a large buffer, auto save turns off temporarily in that buffer.  This is because if you deleted the text unintentionally, you might find the auto-save file more useful if it contains the deleted text.  To reenable auto-saving after this happens, save the buffer with C-x C-s, or use C-u 1 M-x auto-save-mode.



If you want auto-saving to be done in the visited file rather than in a separate auto-save file, enable the global minor mode `auto-save-visited-mode`.  In this mode, auto-saving is identical to explicit saving.  Note that this mode is orthogonal to the `auto-save` mode described above; you can enable both at the same time.  However, if `auto-save` mode is active in some buffer and the obsolete `auto-save-visited-file-name` variable is set to a non-`nil` value, that buffer won’t be affected by `auto-save-visited-mode`.

You can use the variable `auto-save-visited-interval` to customize the interval between auto-save operations in `auto-save-visited-mode`; by default it’s five seconds. `auto-save-interval` and `auto-save-timeout` have no effect on `auto-save-visited-mode`.  See [Controlling Auto-Saving](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Save-Control.html), for details on these variables.



A buffer’s auto-save file is deleted when you save the buffer in its visited file.  (You can inhibit this by setting the variable `delete-auto-save-files` to `nil`.)  Changing the visited file name with C-x C-w or `set-visited-file-name` renames any auto-save file to go with the new visited name.



Killing a buffer, by default, doesn’t remove the buffer’s auto-save file.  If `kill-buffer-delete-auto-save-files` is non-`nil`, killing a buffer that has an auto-save file will make Emacs prompt the user for whether the auto-save file should be deleted.  (This is inhibited if `delete-auto-save-files` is `nil`.)



#### 20.6.2 Controlling Auto-Saving



Each time you visit a file, auto-saving is turned on for that file’s buffer if the variable `auto-save-default` is non-`nil` (but not in batch mode; see [Initial Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/Initial-Options.html)).  The default for this variable is `t`, so auto-saving is the usual practice for file-visiting buffers.  To toggle auto-saving in the current buffer, type M-x auto-save-mode.  Auto Save mode acts as a buffer-local minor mode (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)).



Emacs auto-saves periodically based on how many characters you have typed since the last auto-save.  The variable `auto-save-interval` specifies how many characters there are between auto-saves.  By default, it is 300.  Emacs doesn’t accept values that are too small: if you customize `auto-save-interval` to a value less than 20, Emacs will behave as if the value is 20.



Auto-saving also takes place when you stop typing for a while.  By default, it does this after 30 seconds of idleness (at this time, Emacs may also perform garbage collection; see [Garbage Collection](https://www.gnu.org/software/emacs/manual/html_node/elisp/Garbage-Collection.html#Garbage-Collection) in The Emacs Lisp Reference Manual).  To change this interval, customize the variable `auto-save-timeout`.  The actual time period is longer if the current buffer is long; this is a heuristic which aims to keep out of your way when you are editing long buffers, in which auto-save takes an appreciable amount of time. Auto-saving during idle periods accomplishes two things: first, it makes sure all your work is saved if you go away from the terminal for a while; second, it may avoid some auto-saving while you are actually typing.



When `auto-save-visited-mode` is enabled, Emacs will auto-save file-visiting buffers after five seconds of idle time.  You can customize the variable `auto-save-visited-interval` to change the idle time interval.

Emacs also does auto-saving whenever it gets a fatal error.  This includes killing the Emacs job with a shell command such as ‘kill %emacs’, or disconnecting a phone line or network connection.



You can perform an auto-save explicitly with the command M-x do-auto-save.



#### 20.6.3 Recovering Data from Auto-Saves



You can use the contents of an auto-save file to recover from a loss of data with the command M-x recover-file RET file RET.  This visits file and then (after your confirmation) restores the contents from its auto-save file #file#. You can then save with C-x C-s to put the recovered text into file itself.  For example, to recover file foo.c from its auto-save file #foo.c#, do:

```
M-x recover-file RET foo.c RET
yes RET
C-x C-s
```

Before asking for confirmation, M-x recover-file displays a directory listing describing the specified file and the auto-save file, so you can compare their sizes and dates.  If the auto-save file is older, M-x recover-file does not offer to read it.

When M-x recover-file asks for confirmation, if you answer with diff or =, it shows the diffs between file and its auto-save file #file# and reprompts you for confirmation.



If Emacs or the computer crashes, you can recover all the files you were editing from their auto save files with the command M-x recover-session.  This first shows you a list of recorded interrupted sessions.  Move point to the one you choose, and type C-c C-c.

Then `recover-session` asks about each of the files that were being edited during that session, asking whether to recover that file. If you answer y, it calls `recover-file`, which works in its normal fashion.  It shows the dates of the original file and its auto-save file, and asks once again whether to recover that file.

When `recover-session` is done, the files you’ve chosen to recover are present in Emacs buffers.  You should then save them.  Only this—saving them—updates the files themselves.



Emacs records information about interrupted sessions in files named .saves-pid-hostname~ in the directory ~/.emacs.d/auto-save-list/.  This directory is determined by the variable `auto-save-list-file-prefix`.  If you set `auto-save-list-file-prefix` to `nil`, sessions are not recorded for recovery.



### 20.7 File Name Aliases



Symbolic links and hard links both make it possible for several file names to refer to the same file.  Hard links are alternate names that refer directly to the file; all the names are equally valid, and no one of them is preferred.  By contrast, a symbolic link is a kind of defined alias: when foo is a symbolic link to bar, you can use either name to refer to the file, but bar is the real name, while foo is just an alias.  More complex cases occur when symbolic links point to directories.



Normally, if you visit a file which Emacs is already visiting under a different name, Emacs displays a message in the echo area and uses the existing buffer visiting that file.  This can happen on systems that support hard or symbolic links, or if you use a long file name on a system that truncates long file names, or on a case-insensitive file system.  You can suppress the message by setting the variable `find-file-suppress-same-file-warnings` to a non-`nil` value.  You can disable this feature entirely by setting the variable `find-file-existing-other-name` to `nil`: then if you visit the same file under two different names, you get a separate buffer for each file name.



If the variable `find-file-visit-truename` is non-`nil`, then the file name recorded for a buffer is the file’s *truename* (made by replacing all symbolic links with their target names), rather than the name you specify.  Setting `find-file-visit-truename` also implies the effect of `find-file-existing-other-name`.



Sometimes, a directory is ordinarily accessed through a symbolic link, and you may want Emacs to preferentially show its linked name.  To do this, customize `directory-abbrev-alist`.  Each element in this list should have the form `(from . to)`, which means to replace from with to whenever from appears in a directory name.  The from string is a regular expression (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)).  It is matched against directory names anchored at the first character, and should start with ‘\`’ (to support directory names with embedded newlines, which would defeat ‘^’).  The to string should be an ordinary absolute directory name pointing to the same directory.  Do not use ‘~’ to stand for a home directory in the to string; Emacs performs these substitutions separately.  Here’s an example, from a system on which /home/fsf is normally accessed through a symbolic link named /fsf:

```
(("\\`/home/fsf" . "/fsf"))
```



### 20.8 File Directories



The file system groups files into *directories*.  A *directory listing* is a list of all the files in a directory.  Emacs provides commands to create and delete directories, and to make directory listings in brief format (file names only) and verbose format (sizes, dates, and other attributes included).  Emacs also includes a directory browser feature called Dired, which you can invoke with C-x d; see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html).

- C-x C-d dir-or-pattern RET

  Display a brief directory listing (`list-directory`).

- C-u C-x C-d dir-or-pattern RET

  Display a verbose directory listing.

- M-x make-directory RET dirname RET

  Create a new directory named dirname.

- M-x delete-directory RET dirname RET

  Delete the directory named dirname.  If it isn’t empty, you will be asked whether you want to delete it recursively.



The command to display a directory listing is C-x C-d (`list-directory`).  It reads using the minibuffer a file name which is either a directory to be listed or a wildcard-containing pattern for the files to be listed.  For example,

```
C-x C-d /u2/emacs/etc RET
```

lists all the files in directory /u2/emacs/etc.  Here is an example of specifying a file name pattern:

```
C-x C-d /u2/emacs/src/*.c RET
```

Normally, C-x C-d displays a brief directory listing containing just file names.  A numeric argument (regardless of value) tells it to make a verbose listing including sizes, dates, and owners (like ‘ls -l’).



The text of a directory listing is mostly obtained by running `ls` in an inferior process.  Two Emacs variables control the switches passed to `ls`: `list-directory-brief-switches` is a string giving the switches to use in brief listings (`"-CF"` by default), and `list-directory-verbose-switches` is a string giving the switches to use in a verbose listing (`"-l"` by default).

In verbose directory listings, Emacs adds information about the amount of free space on the disk that contains the directory.

The command M-x delete-directory prompts for a directory’s name using the minibuffer, and deletes the directory if it is empty.  If the directory is not empty, you will be asked whether you want to delete it recursively.  On systems that have a “Trash” (or “Recycle Bin”) feature, you can make this command move the specified directory to the Trash instead of deleting it outright, by changing the variable `delete-by-moving-to-trash` to `t`.  See [Miscellaneous File Operations](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-File-Ops.html), for more information about using the Trash.



### 20.9 Comparing Files



The command M-x diff prompts for two file names, using the minibuffer, and displays the differences between the two files in a buffer named *diff*.  This works by running the `diff` program, using options taken from the variable `diff-switches`. The value of `diff-switches` should be a string; the default is `"-u"` to specify a unified context diff. See [Diff](https://www.gnu.org/software/diffutils/manual/html_node/index.html#Top) in Comparing and Merging Files, for more information about the `diff` program.

The output of the `diff` command is shown using a major mode called Diff mode.  See [Diff Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Diff-Mode.html).

A (much more sophisticated) alternative is M-x ediff (see [Ediff](https://www.gnu.org/software/emacs/manual/html_node/ediff/index.html#Top) in The Ediff Manual).



The command M-x diff-backup compares a specified file with its most recent backup.  If you specify the name of a backup file, `diff-backup` compares it with the source file that it is a backup of.  In all other respects, this behaves like M-x diff.



The command M-x diff-buffer-with-file compares a specified buffer with its corresponding file.  This shows you what changes you would make to the file if you save the buffer.



The command M-x diff-buffers compares the contents of two specified buffers.



The command M-x compare-windows compares the text in the current window with that in the window that was the selected window before you selected the current one.  (For more information about windows in Emacs, see [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html).)  Comparison starts at point in each window, after pushing each initial point value on the mark ring (see [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html)) in its respective buffer.  Then it moves point forward in each window, one character at a time, until it reaches characters that don’t match.  Then the command exits.

If point in the two windows is followed by non-matching text when the command starts, M-x compare-windows tries heuristically to advance up to matching text in the two windows, and then exits.  So if you use M-x compare-windows repeatedly (see [Repeating a Command](https://www.gnu.org/software/emacs/manual/html_node/emacs/Repeating.html)), each time it either skips one matching range or finds the start of another.



With a numeric argument, `compare-windows` ignores changes in whitespace.  If the variable `compare-ignore-case` is non-`nil`, the comparison ignores differences in case as well. If the variable `compare-ignore-whitespace` is non-`nil`, `compare-windows` by default ignores changes in whitespace, but a prefix argument turns that off for that single invocation of the command.



You can use M-x smerge-mode to turn on Smerge mode, a minor mode for editing output from the `diff3` program.  This is typically the result of a failed merge from a version control system update outside VC, due to conflicting changes to a file.  Smerge mode provides commands to resolve conflicts by selecting specific changes.

See [Merging Files with Emerge](https://www.gnu.org/software/emacs/manual/html_node/emacs/Emerge.html), for the Emerge facility, which provides a powerful interface for merging files.



### 20.10 Diff Mode



Diff mode is a major mode used for the output of M-x diff and other similar commands.  This kind of output is called a *patch*, because it can be passed to the `patch` command to automatically apply the specified changes.  To select Diff mode manually, type M-x diff-mode.



The changes specified in a patch are grouped into *hunks*, which are contiguous chunks of text that contain one or more changed lines. Hunks usually also include unchanged lines to provide context for the changes.  Each hunk is preceded by a *hunk header*, which specifies the old and new line numbers where the hunk’s changes occur. Diff mode highlights each hunk header, to distinguish it from the actual contents of the hunk.

The first hunk in a patch is preceded by a file header, which shows the names of the new and the old versions of the file, and their time stamps.  If a patch shows changes for more than one file, each file has such a header before the first hunk of that file’s changes.



You can edit a Diff mode buffer like any other buffer.  (If it is read-only, you need to make it writable first; see [Miscellaneous Buffer Operations](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Buffer.html).) Whenever you edit a hunk, Diff mode attempts to automatically correct the line numbers in the hunk headers, to ensure that the patch remains correct, and could still be applied by `patch`.  To disable automatic line number correction, change the variable `diff-update-on-the-fly` to `nil`.

Diff mode arranges for hunks to be treated as compiler error messages by M-g M-n and other commands that handle error messages (see [Compilation Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compilation-Mode.html)).  Thus, you can use the compilation-mode commands to visit the corresponding source locations.

In addition, Diff mode provides the following commands to navigate, manipulate and apply parts of patches:

- M-n

  Move to the next hunk-start (`diff-hunk-next`).  With prefix argument n, move forward to the nth next hunk.  By default, Diff mode *refines* hunks as Emacs displays them, highlighting their changes with better granularity.  Alternatively, if you set `diff-refine` to the symbol `navigation`, Diff mode only refines the hunk you move to with this command or with `diff-hunk-prev`.

- M-p

  Move to the previous hunk-start (`diff-hunk-prev`).  With prefix argument n, move back to the nth previous hunk.  Like M-n, this command refines the hunk you move to if you set `diff-refine` to the symbol `navigation`.

- M-}

  Move to the next file-start, in a multi-file patch (`diff-file-next`).  With prefix argument n, move forward to the start of the nth next file.

- M-{

  Move to the previous file-start, in a multi-file patch (`diff-file-prev`).  With prefix argument n, move back to the start of the nth previous file.

- M-k

  Kill the hunk at point (`diff-hunk-kill`).

- M-K

  In a multi-file patch, kill the current file part. (`diff-file-kill`).

- C-c C-a

  Apply this hunk to its target file (`diff-apply-hunk`).  With a prefix argument of C-u, revert this hunk, i.e. apply the reverse of the hunk, which changes the “new” version into the “old” version.  If `diff-jump-to-old-file` is non-`nil`, apply the hunk to the “old” version of the file instead.

- C-c RET a

  Apply all the hunks in the buffer (`diff-apply-buffer`).  If the diffs were applied successfully, save the changed buffers.

- C-c C-b

  Highlight the changes of the hunk at point with a finer granularity (`diff-refine-hunk`).  This allows you to see exactly which parts of each changed line were actually changed.  By default, Diff mode refines hunks as Emacs displays them, so you may find this command useful if you customize `diff-refine` to a non-default value.

- C-c C-c

  Go to the source file and line corresponding to this hunk (`diff-goto-source`).  By default, this jumps to the “new” version of the file, the one shown first on the file header. With a prefix argument, jump to the “old” version instead.  If `diff-jump-to-old-file` is non-`nil`, this command by default jumps to the “old” file, and the meaning of the prefix argument is reversed.  If the prefix argument is a number greater than 8 (e.g., if you type C-u C-u C-c C-c), then this command also sets `diff-jump-to-old-file` for the next invocation. If the source file is under version control (see [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html)), this jumps to the work file by default.  With a prefix argument, jump to the “old” revision of the file (see [Examining And Comparing Old Revisions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Old-Revisions.html)), when point is on the old line, or otherwise jump to the “new” revision.

- C-c C-e

  Start an Ediff session with the patch (`diff-ediff-patch`). See [Ediff](https://www.gnu.org/software/emacs/manual/html_node/ediff/index.html#Top) in The Ediff Manual.

- C-c C-n

  Restrict the view to the current hunk (`diff-restrict-view`). See [Narrowing](https://www.gnu.org/software/emacs/manual/html_node/emacs/Narrowing.html).  With a prefix argument, restrict the view to the current file of a multiple-file patch.  To widen again, use C-x n w (`widen`).

- C-c C-r

  Reverse the direction of comparison for the entire buffer (`diff-reverse-direction`).  With a prefix argument, reverse the direction only inside the current region (see [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html)).  Reversing the direction means changing the hunks and the file-start headers to produce a patch that would change the “new” version into the “old” one.

- C-c C-s

  Split the hunk at point (`diff-split-hunk`) into two separate hunks.  This inserts a hunk header and modifies the header of the current hunk.  This command is useful for manually editing patches, and only works with the *unified diff format* produced by the -u or --unified options to the `diff` program.  If you need to split a hunk in the *context diff format* produced by the -c or --context options to `diff`, first convert the buffer to the unified diff format with C-c C-u.

- C-c C-d

  Convert the entire buffer to the *context diff format* (`diff-unified->context`).  With a prefix argument, convert only the hunks within the region.

- C-c C-u

  Convert the entire buffer to unified diff format (`diff-context->unified`).  With a prefix argument, convert unified format to context format.  When the mark is active, convert only the hunks within the region.

- C-c C-l

  Re-generate the current hunk (`diff-refresh-hunk`).

- C-c C-w

  Re-generate the current hunk, disregarding changes in whitespace. With a non-`nil` prefix arg, re-generate all the hunks (`diff-ignore-whitespace-hunk`).  This calls `diff-command` with `diff-ignore-whitespace-switches`, which defaults to ‘-b’, meaning ignore changes in whitespace only.

- C-x 4 A

  Generate a ChangeLog entry, like C-x 4 a does (see [Change Logs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Change-Log.html)), for each one of the hunks (`diff-add-change-log-entries-other-window`).  This creates a skeleton of the log of changes that you can later fill with the actual descriptions of the changes.  C-x 4 a itself in Diff mode operates on behalf of the current hunk’s file, but gets the function name from the patch itself.  This is useful for making log entries for functions that are deleted by the patch.



Patches sometimes include trailing whitespace on modified lines, as an unintentional and undesired change.  There are two ways to deal with this problem.  Firstly, if you enable Whitespace mode in a Diff buffer (see [Useless Whitespace](https://www.gnu.org/software/emacs/manual/html_node/emacs/Useless-Whitespace.html)), it automatically highlights trailing whitespace in modified lines.  Secondly, you can use the command M-x diff-delete-trailing-whitespace, which searches for trailing whitespace in the lines modified by the patch, and removes that whitespace in both the patch and the patched source file(s). This command does not save the modifications that it makes, so you can decide whether to save the changes (the list of modified files is displayed in the echo area).  With a prefix argument, it tries to modify the original (“old”) source files rather than the patched (“new”) source files.



If `diff-font-lock-syntax` is non-`nil`, fragments of source in hunks are highlighted according to the appropriate major mode.



### 20.11 Copying, Naming and Renaming Files

Emacs has several commands for copying, naming, and renaming files. All of them read two file names, old (or target) and new, using the minibuffer, and then copy or adjust a file’s name accordingly; they do not accept wildcard file names.

In all these commands, if the argument new is just a directory name (see [Directory Names](https://www.gnu.org/software/emacs/manual/html_node/elisp/Directory-Names.html#Directory-Names) in the Emacs Lisp Reference Manual), the real new name is in that directory, with the same non-directory component as old.  For example, the command M-x rename-file RET ~/foo RET /tmp/ RET renames ~/foo to /tmp/foo.  On GNU and other POSIX-like systems, directory names end in ‘/’.

All these commands ask for confirmation when the new file name already exists.



M-x copy-file copies the contents of the file old to the file new.



M-x copy-directory copies directories, similar to the `cp -r` shell command.  If new is a directory name, it creates a copy of the old directory and puts it in new. Otherwise it copies all the contents of old into a new directory named new.  If `copy-directory-create-symlink` is non-`nil` and old is a symbolic link, this command will copy the symbolic link.  If `nil`, this command will follow the link and copy the contents instead.  (This is the default.)



M-x rename-file renames file old as new.  If the file name new already exists, you must confirm with yes or renaming is not done; this is because renaming causes the old meaning of the name new to be lost.  If old and new are on different file systems, the file old is copied and deleted.

If a file is under version control (see [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html)), you should rename it using M-x vc-rename-file instead of M-x rename-file.  See [Deleting and Renaming Version-Controlled Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/VC-Delete_002fRename.html).



M-x add-name-to-file adds an additional name to an existing file without removing the old name.  The new name is created as a hard link to the existing file.  The new name must belong on the same file system that the file is on.  On MS-Windows, this command works only if the file resides in an NTFS file system.  On MS-DOS, and some remote system types, it works by copying the file.



M-x make-symbolic-link creates a symbolic link named new, which points at target.  The effect is that future attempts to open file new will refer to whatever file is named target at the time the opening is done, or will get an error if the name target is nonexistent at that time.  This command does not expand the argument target, so that it allows you to specify a relative name as the target of the link.  However, this command does expand leading ‘~’ in target so that you can easily specify home directories, and strips leading ‘/:’ so that you can specify relative names beginning with literal ‘~’ or ‘/:’. See [Quoted File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quoted-File-Names.html).  On MS-Windows, this command works only on MS Windows Vista and later.  When new is remote, it works depending on the system type.



### 20.12 Miscellaneous File Operations

Emacs has commands for performing many other operations on files. All operate on one file; they do not accept wildcard file names.



M-x delete-file prompts for a file and deletes it.  If you are deleting many files in one directory, it may be more convenient to use Dired rather than `delete-file`.  See [Deleting Files with Dired](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired-Deletion.html).



M-x move-file-to-trash moves a file into the system *Trash* (or *Recycle Bin*).  This is a facility available on most operating systems; files that are moved into the Trash can be brought back later if you change your mind.  (The way to restore trashed files is system-dependent.)



By default, Emacs deletion commands do *not* use the Trash.  To use the Trash (when it is available) for common deletion commands, change the variable `delete-by-moving-to-trash` to `t`. This affects the commands M-x delete-file and M-x delete-directory (see [File Directories](https://www.gnu.org/software/emacs/manual/html_node/emacs/Directories.html)), as well as the deletion commands in Dired (see [Deleting Files with Dired](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired-Deletion.html)).  Supplying a prefix argument to M-x delete-file or M-x delete-directory makes them delete outright, instead of using the Trash, regardless of `delete-by-moving-to-trash`.

If you have `delete-by-moving-to-trash` set, and you want to delete files manually in Emacs from the Trash directory, using commands like D (`dired-do-delete`) doesn’t work well in the Trash directory (it’ll just give the file a new name, but won’t delete anything).  If you want to be able to do this, you should create a `.dir-locals.el` file containing something like the following in the Trash directory:

```
((dired-mode . ((delete-by-moving-to-trash . nil))))
```

Note, however, if you use the system “empty trash” command, it’s liable to also delete this `.dir-locals.el` file, so this should only be done if you delete files from the Trash directory manually.



If the variable `remote-file-name-inhibit-delete-by-moving-to-trash` is non-`nil`, remote files are never moved to the Trash.  They are deleted instead.

If a file is under version control (see [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html)), you should delete it using M-x vc-delete-file instead of M-x delete-file.  See [Deleting and Renaming Version-Controlled Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/VC-Delete_002fRename.html).



M-x insert-file (also C-x i) inserts a copy of the contents of the specified file into the current buffer at point, leaving point unchanged before the contents.  The position after the inserted contents is added to the mark ring, without activating the mark (see [The Mark Ring](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark-Ring.html)).



M-x insert-file-literally is like M-x insert-file, except the file is inserted literally: it is treated as a sequence of ASCII characters with no special encoding or conversion, similar to the M-x find-file-literally command (see [Visiting Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visiting.html)).



M-x write-region is the inverse of M-x insert-file; it copies the contents of the region into the specified file.  M-x append-to-file adds the text of the region to the end of the specified file.  See [Accumulating Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Accumulating-Text.html).  The variable `write-region-inhibit-fsync` applies to these commands, as well as saving files; see [Customizing Saving of Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customize-Save.html).



M-x set-file-modes reads a file name followed by a *file mode*, and applies that file mode to the specified file.  File modes, also called *file permissions*, determine whether a file can be read, written to, or executed, and by whom.  This command reads file modes using the same symbolic or octal format accepted by the `chmod` command; for instance, ‘u+x’ means to add execution permission for the user who owns the file.  It has no effect on operating systems that do not support file modes.  `chmod` is a convenience alias for this function.



### 20.13 Accessing Compressed Files



Emacs automatically uncompresses compressed files when you visit them, and automatically recompresses them if you alter them and save them.  Emacs recognizes compressed files by their file names.  File names ending in ‘.gz’ indicate a file compressed with `gzip`.  Other endings indicate other compression programs.

Automatic uncompression and compression apply to all the operations in which Emacs uses the contents of a file.  This includes visiting it, saving it, inserting its contents into a buffer, loading it, and byte compiling it.



To disable this feature, type the command M-x auto-compression-mode.  You can disable it permanently by customizing the variable `auto-compression-mode`.



### 20.14 File Archives



A file whose name ends in ‘.tar’ is normally an *archive* made by the `tar` program.  Emacs views these files in a special mode called Tar mode which provides a Dired-like list of the contents (see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html)).  You can move around through the list just as you would in Dired, and visit the subfiles contained in the archive. However, not all Dired commands are available in Tar mode.

If Auto Compression mode is enabled (see [Accessing Compressed Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compressed-Files.html)), then Tar mode is used also for compressed archives—files with extensions ‘.tgz’, `.tar.Z` and `.tar.gz`.

The keys e, f and RET all extract a component file into its own buffer.  You can edit it there, and if you save the buffer, the edited version will replace the version in the Tar buffer. Clicking with the mouse on the file name in the Tar buffer does likewise.  v extracts a file into a buffer in View mode (see [View Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/View-Mode.html)).  o extracts the file and displays it in another window, so you could edit the file and operate on the archive simultaneously.

The I key adds a new (regular) file to the archive.  The file is initially empty, but can readily be edited using the commands above.  The command inserts the new file before the current one, so that using it on the topmost line of the Tar buffer makes the new file the first one in the archive, and using it at the end of the buffer makes it the last one.

d marks a file for deletion when you later use x, and u unmarks a file, as in Dired.  C copies a file from the archive to disk and R renames a file within the archive. g reverts the buffer from the archive on disk.  The keys M, G, and O change the file’s permission bits, group, and owner, respectively.

Saving the Tar buffer writes a new version of the archive to disk with the changes you made to the components.

You don’t need the `tar` program to use Tar mode—Emacs reads the archives directly.  However, accessing compressed archives requires the appropriate uncompression program.



A separate but similar Archive mode is used for `arc`, `jar`, `lzh`, `zip`, `rar`, `7z`, and `zoo` archives, as well as `exe` files that are self-extracting executables.

The key bindings of Archive mode are similar to those in Tar mode, with the addition of the m key which marks a file for subsequent operations, and M-DEL which unmarks all the marked files. Also, the a key toggles the display of detailed file information, for those archive types where it won’t fit in a single line.  Operations such as renaming a subfile, or changing its mode or owner, are supported only for some of the archive formats.

Unlike Tar mode, Archive mode runs the archiving programs to unpack and repack archives.  However, you don’t need these programs to look at the archive table of contents, only to extract or manipulate the subfiles in the archive.  Details of the program names and their options can be set in the ‘Archive’ Customize group (see [Customization Groups](https://www.gnu.org/software/emacs/manual/html_node/emacs/Customization-Groups.html)).



### 20.15 Remote Files



You can refer to files on other machines using a special file name syntax:

```
/method:host:filename
/method:user@host:filename
/method:user@host#port:filename
```

To carry out this request, Emacs uses a remote-login program such as `ssh`. You must always specify in the file name which method to use—for example, /ssh:user@host:filename uses `ssh`.  When you specify the pseudo method ‘-’ in the file name, Emacs chooses the method as follows:

1.  If the host name starts with ‘ftp.’ (with dot), Emacs uses FTP.
2.  If the user name is ‘ftp’ or ‘anonymous’, Emacs uses FTP.
3.  If the variable `tramp-default-method` is set to ‘ftp’, Emacs uses FTP.
4.  If `ssh-agent` is running, Emacs uses `scp`.
5.  Otherwise, Emacs uses `ssh`.



You can entirely turn off the remote file name feature by running M-x inhibit-remote-files.  You can turn off the feature in individual cases by quoting the file name with ‘/:’ (see [Quoted File Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quoted-File-Names.html)).



Remote file access through FTP is handled by the Ange-FTP package, which is documented in the following.  Remote file access through the other methods is handled by the Tramp package, which has its own manual. See [The Tramp Manual](https://www.gnu.org/software/emacs/manual/html_node/tramp/index.html#Top) in The Tramp Manual.



When the Ange-FTP package is used, Emacs logs in through FTP using the name user, if that is specified in the remote file name.  If user is unspecified, Emacs logs in using your user name on the local system; but if you set the variable `ange-ftp-default-user` to a string, that string is used instead.  When logging in, Emacs may also ask for a password.



For performance reasons, Emacs does not make backup files for files accessed via FTP by default.  To make it do so, change the variable `ange-ftp-make-backup-files` to a non-`nil` value.

By default, auto-save files for remote files are made in the temporary file directory on the local machine, as specified by the variable `auto-save-file-name-transforms`.  See [Auto-Save Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Save-Files.html).



To visit files accessible by anonymous FTP, you use special user names ‘anonymous’ or ‘ftp’.  Passwords for these user names are handled specially.  The variable `ange-ftp-generate-anonymous-password` controls what happens: if the value of this variable is a string, then that string is used as the password; if non-`nil` (the default), then the value of `user-mail-address` is used; if `nil`, then Emacs prompts you for a password as usual (see [Entering passwords](https://www.gnu.org/software/emacs/manual/html_node/emacs/Passwords.html)).



Sometimes you may be unable to access files on a remote machine because a *firewall* in between blocks the connection for security reasons.  If you can log in on a *gateway* machine from which the target files *are* accessible, and whose FTP server supports gatewaying features, you can still use remote file names; all you have to do is specify the name of the gateway machine by setting the variable `ange-ftp-gateway-host`, and set `ange-ftp-smart-gateway` to `t`.  Otherwise you may be able to make remote file names work, but the procedure is complex.  You can read the instructions by typing M-x finder-commentary RET ange-ftp RET.



### 20.16 Quoted File Names



You can *quote* an absolute file name to prevent special characters and syntax in it from having their special effects. The way to do this is to add ‘/:’ at the beginning.

For example, you can quote a local file name which appears remote, to prevent it from being treated as a remote file name.  Thus, if you have a directory named /foo: and a file named bar in it, you can refer to that file in Emacs as ‘/:/foo:/bar’.

If you want to quote only special characters in the local part of a remote file name, you can quote just the local part. ‘/ssh:baz:/:/foo:/bar’ refers to the file bar of directory /foo: on the host baz.

‘/:’ can also prevent ‘~’ from being treated as a special character for a user’s home directory.  For example, /:/tmp/~hack refers to a file whose name is ~hack in directory /tmp.

Quoting with ‘/:’ is also a way to enter in the minibuffer a file name that contains ‘$’.  In order for this to work, the ‘/:’ must be at the beginning of the minibuffer contents.  (You can also double each ‘$’; see [File Names with $](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Names.html#File-Names-with-_0024).)

You can also quote wildcard characters with ‘/:’, for visiting. For example, /:/tmp/foo*bar visits the file /tmp/foo*bar.

Another method of getting the same result is to enter /tmp/foo[*]bar, which is a wildcard specification that matches only /tmp/foo*bar.  However, in many cases there is no need to quote the wildcard characters because even unquoted they give the right result.  For example, if the only file name in /tmp that starts with ‘foo’ and ends with ‘bar’ is foo*bar, then specifying /tmp/foo*bar will visit only /tmp/foo*bar.



### 20.17 File Name Cache



You can use the *file name cache* to make it easy to locate a file by name, without having to remember exactly where it is located. When typing a file name in the minibuffer, C-TAB (`file-cache-minibuffer-complete`) completes it using the file name cache.  If you repeat C-TAB, that cycles through the possible completions of what you had originally typed.  (However, note that the C-TAB character cannot be typed on most text terminals.)

The file name cache does not fill up automatically.  Instead, you load file names into the cache using these commands:



- M-x file-cache-add-directory RET directory RET

  Add each file name in directory to the file name cache.

- M-x file-cache-add-directory-using-find RET directory RET

  Add each file name in directory and all of its nested subdirectories to the file name cache.

- M-x file-cache-add-directory-using-locate RET directory RET

  Add each file name in directory and all of its nested subdirectories to the file name cache, using `locate` to find them all.

- M-x file-cache-add-directory-list RET variable RET

  Add each file name in each directory listed in variable to the file name cache.  variable should be a Lisp variable whose value is a list of directories, like `load-path`.

- M-x file-cache-clear-cache RET

  Clear the cache; that is, remove all file names from it.

The file name cache is not persistent: it is kept and maintained only for the duration of the Emacs session.  You can view the contents of the cache with the `file-cache-display` command.



### 20.18 Convenience Features for Finding Files

In this section, we introduce some convenient facilities for finding recently-opened files, reading file names from a buffer.



If you enable Recentf mode, with M-x recentf-mode, Emacs maintains a list of recently opened files.  To open a file from this list, use the M-x recentf-open command.  When this mode is enabled, the ‘File’ menu will include a submenu that you can use to visit one of these files.  M-x recentf-save-list saves the current `recentf-list` to a file, and M-x recentf-edit-list edits it.



If you use remote files, you might also consider customizing `remote-file-name-access-timeout`, which is the number of seconds after which the check whether a remote file shall be used in Recentf is stopped.  This prevents Emacs being blocked.

The M-x ffap command generalizes `find-file` with more powerful heuristic defaults (see [Finding Files and URLs at Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/FFAP.html)), often based on the text at point.  Partial Completion mode offers other features extending `find-file`, which can be used with `ffap`. See [Completion Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Options.html).



### 20.19 Viewing Image Files



Visiting image files automatically selects Image mode.  In this major mode, you can type C-c C-c (`image-toggle-display`) to toggle between displaying the file as an image in the Emacs buffer, and displaying its underlying text (or raw byte) representation. Additionally you can type C-c C-x (`image-toggle-hex-display`) to toggle between displaying the file as an image in the Emacs buffer, and displaying it in hex representation.  Displaying the file as an image works only if Emacs is compiled with support for displaying such images.



If the displayed image is wider or taller than the window in which it is displayed, the usual point motion keys (C-f, C-p, and so forth) cause different parts of the image to be displayed. However, by default images are resized automatically to fit the window, so this is only necessary if you customize the default behavior by using the options `image-auto-resize` and `image-auto-resize-on-window-resize`.



To resize the image manually you can use the command `image-transform-fit-to-window` bound to s w that fits the image to both the window height and width.  To scale the image to a percentage of its original size, use the command `image-transform-set-percent` bound to s p.  To scale the image specifying a scale factor, use the command `image-transform-set-scale` bound to s s.  To reset all transformations to the initial state, use `image-transform-reset-to-initial` bound to s 0, or `image-transform-reset-to-original` bound to s o.



You can press n (`image-next-file`) and p (`image-previous-file`) to visit the next image file and the previous image file in the same directory, respectively.  These commands will consult the “parent” dired buffer to determine what the next/previous image file is.  These commands also work when opening a file from archive files (like zip or tar files), and will then instead consult the archive mode buffer.  If neither an archive nor a dired “parent” buffer can be found, a dired buffer is opened.



When looking through images, it’s sometimes convenient to be able to mark the files for later processing (for instance, if you want to select a group of images to copy somewhere else).  The m (`image-mode-mark-file`) command will mark the current file in any Dired buffer(s) that display the current file’s directory.  If no such buffer is open, the directory is opened in a new buffer.  To unmark files, use the u (`image-mode-unmark-file`) command. Finally, if you just want to copy the current buffers file name to the kill ring, you can use the w (`image-mode-copy-file-name-as-kill`) command.



If the image can be animated, the command RET (`image-toggle-animation`) starts or stops the animation. Animation plays once, unless the option `image-animate-loop` is non-`nil`.  With f (`image-next-frame`) and b (`image-previous-frame`) you can step through the individual frames.  Both commands accept a numeric prefix to step through several frames at once.  You can go to a specific frame with F (`image-goto-frame`).  Frames are indexed from 1.  Typing a + (`image-increase-speed`) increases the speed of the animation, a - (`image-decrease-speed`) decreases it, and a r (`image-reverse-speed`) reverses it.  The command a 0 (`image-reset-speed`) resets the speed to the original value.

In addition to the above key bindings, which are specific to Image mode, images shown in any Emacs buffer have special key bindings when point is at or inside the image:

- i +

  Increase the image size (`image-increase-size`) by 20%.  Prefix numeric argument controls the increment; the value of n means to multiply the size by the factor of `1 + n / 10`, so C-u 5 i + means to increase the size by 50%.

- i -

  Decrease the image size (`image-decrease-size`) by 20%.  Prefix numeric argument controls the decrement; the value of n means to multiply the size by the factor of `1 - n / 10`, so C-u 3 i - means to decrease the size by 30%.

- i r

  Rotate the image by 90 degrees clockwise (`image-rotate`). With the prefix argument, rotate by 90 degrees counter-clockwise instead. Note that this command is not available for sliced images.

- i h

  Flip the image horizontally (`image-flip-horizontally`).  This presents the image as if reflected in a vertical mirror. Note that this command is not available for sliced images.

- i v

  Flip the image vertically (`image-flip-vertically`).  This presents the image as if reflected in a horizontal mirror. Note that this command is not available for sliced images.

- i o

  Save the image to a file (`image-save`).  This command prompts you for the name of the file to save the image.

- i c

  Crop the image (`image-crop`).  This command is available only if your system has an external program installed that can be used for cropping and cutting of images; the user option `image-crop-crop-command` determines what program to use, and defaults to the ImageMagick’s `convert` program.  The command displays the image with a rectangular frame superimposed on it, and lets you use the mouse to move and resize the frame.  Type m to cause mouse movements to move the frame instead of resizing it; type s to move a square frame instead.  When you are satisfied with the position and size of the cropping frame, type RET to actually crop the part under the frame; or type q to exit without cropping.  You can then save the cropped image using i o or M-x image-save.

- i x

  Cut a rectangle from the image (`image-cut`).  This works the same as `image-crop` (and also requires an external program, defined by the variable `image-crop-cut-command`, to perform the image cut), but instead of cropping the image, it removes the part inside the frame and fills that part with the color specified by `image-cut-color`.  With prefix argument, the command prompts for the color to use.

The size and rotation commands are “repeating”, which means that you can continue adjusting the image without using the i prefix.



If Emacs was compiled with support for the ImageMagick library, it can use ImageMagick to render a wide variety of images.  The variable `imagemagick-enabled-types` lists the image types that Emacs may render using ImageMagick; each element in the list should be an internal ImageMagick name for an image type, as a symbol or an equivalent string (e.g., `BMP` for .bmp images).  To enable ImageMagick for all possible image types, change `imagemagick-enabled-types` to `t`.  The variable `imagemagick-types-inhibit` lists the image types which should never be rendered using ImageMagick, regardless of the value of `imagemagick-enabled-types` (the default list includes types like `C` and `HTML`, which ImageMagick can render as an image but Emacs should not).  To disable ImageMagick entirely, change `imagemagick-types-inhibit` to `t`.



If Emacs doesn’t have native support for the image format in question, and `image-use-external-converter` is non-`nil`, Emacs will try to determine whether there are external utilities that can be used to transform the image in question to PNG before displaying.  GraphicsMagick, ImageMagick and `ffmpeg` are currently supported for image conversions.



In addition, you may wish to add special handlers for certain image formats.  These can be added with the `image-converter-add-handler` function.  For instance, to allow viewing Krita files as simple images, you could say something like:

```
(image-converter-add-handler
 "kra"
 (lambda (file data-p)
   (if data-p
       (error "Can't decode non-files")
     (call-process "unzip" nil t nil
                   "-qq" "-c" "-x" file "mergedimage.png"))))
```

The function takes two parameters, where the first is a file name suffix, and the second is a function to do the “conversion”.  This function takes two parameters, where the first is the file name or a string with the data, and the second says whether the first parameter is data or not, and should output an image in `image-convert-to-format` format in the current buffer.



The Image-Dired package can also be used to view images as thumbnails.  See [Viewing Image Thumbnails in Dired](https://www.gnu.org/software/emacs/manual/html_node/emacs/Image_002dDired.html).



### 20.20 Filesets



If you regularly edit a certain group of files, you can define them as a *fileset*.  This lets you perform certain operations, such as visiting, `query-replace`, and shell commands on all the files at once.  To make use of filesets, you must first add the expression `(filesets-init)` to your init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)).  This adds a ‘Filesets’ sub-menu to the menu bar’s ‘File’ menu.



The simplest way to define a fileset is by adding files to it one at a time.  To add a file to fileset name, visit the file and type M-x filesets-add-buffer RET name RET.  If there is no fileset name, this creates a new one, which initially contains only the current file.  The command M-x filesets-remove-buffer removes the current file from a fileset.

You can also edit the list of filesets directly, with M-x filesets-edit (or by choosing ‘Edit Filesets’ from the ‘Filesets’ menu).  The editing is performed in a Customize buffer (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).  Normally, a fileset is a simple list of files, but you can also define a fileset as a regular expression matching file names.  Some examples of these more complicated filesets are shown in the Customize buffer.  Remember to select ‘Save for future sessions’ if you want to use the same filesets in future Emacs sessions.

You can use the command M-x filesets-open to visit all the files in a fileset, and M-x filesets-close to close them.  Use M-x filesets-run-cmd to run a shell command on all the files in a fileset.  These commands are also available from the ‘Filesets’ menu, where each existing fileset is represented by a submenu.

See [Version Control](https://www.gnu.org/software/emacs/manual/html_node/emacs/Version-Control.html), for a different concept of filesets: groups of files bundled together for version control operations. Filesets of that type are unnamed, and do not persist across Emacs sessions.

## 21 Using Multiple Buffers



The text you are editing in Emacs resides in an object called a *buffer*.  Each time you visit a file, a buffer is used to hold the file’s text.  Each time you invoke Dired, a buffer is used to hold the directory listing.  If you send a message with C-x m, a buffer is used to hold the text of the message.  When you ask for a command’s documentation, that appears in a buffer named *Help*.

Buffers exist as long as they are in use, and are deleted (“killed”) when no longer needed, either by you (see [Killing Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Kill-Buffer.html)) or by Emacs (e.g., when you exit Emacs, see [Exiting Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Exiting.html)).

Each buffer has a unique name, which can be of any length.  When a buffer is displayed in a window, its name is shown in the mode line (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)).  The distinction between upper and lower case matters in buffer names.  Most buffers are made by visiting files, and their names are derived from the files’ names; however, you can also create an empty buffer with any name you want.  A newly started Emacs has several buffers, including one named *scratch*, which can be used for evaluating Lisp expressions and is not associated with any file (see [Lisp Interaction Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Lisp-Interaction.html)).



At any time, one and only one buffer is *selected*; we call it the *current buffer*.  We sometimes say that a command operates on “the buffer”; this really means that it operates on the current buffer.  When there is only one Emacs window, the buffer displayed in that window is current.  When there are multiple windows, the buffer displayed in the *selected window* is current.  See [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html).



A buffer’s *contents* consist of a series of characters, each of which optionally carries a set of text properties (see [Text properties](https://www.gnu.org/software/emacs/manual/html_node/emacs/International-Chars.html)) that can specify more information about that character.

Aside from its textual contents, each buffer records several pieces of information, such as what file it is visiting (if any), whether it is modified, and what major mode and minor modes are in effect (see [Major and Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Modes.html)).  These are stored in *buffer-local variables*—variables that can have a different value in each buffer. See [Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Locals.html).



A buffer’s size cannot be larger than some maximum, which is defined by the largest buffer position representable by *Emacs integers*. This is because Emacs tracks buffer positions using that data type. For typical 64-bit machines, this maximum buffer size is *2^{61} - 2* bytes, or about 2 EiB.  For typical 32-bit machines, the maximum is usually *2^{29} - 2* bytes, or about 512 MiB.  Buffer sizes are also limited by the amount of memory in the system.







### 21.1 Creating and Selecting Buffers



- C-x b buffer RET

  Select or create a buffer named buffer (`switch-to-buffer`).

- C-x 4 b buffer RET

  Similar, but select buffer in another window (`switch-to-buffer-other-window`).

- C-x 5 b buffer RET

  Similar, but select buffer in a separate frame (`switch-to-buffer-other-frame`).

- C-x LEFT

  Select the previous buffer in the buffer list (`previous-buffer`).

- C-x RIGHT

  Select the next buffer in the buffer list (`next-buffer`).

- C-u M-g M-g

- C-u M-g g

  Read a number n and move to line n in the most recently selected buffer other than the current buffer, in another window.



The C-x b (`switch-to-buffer`) command reads a buffer name using the minibuffer.  Then it makes that buffer current, and displays it in the currently-selected window.  An empty input specifies the buffer that was current most recently among those not now displayed in any window.

While entering the buffer name, you can use the usual completion and history commands (see [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html)).  Note that C-x b, and related commands, use *permissive completion with confirmation* for minibuffer completion: if you type RET when the minibuffer text names a nonexistent buffer, Emacs prints ‘[Confirm]’ and you must type a second RET to submit that buffer name. See [Completion Exit](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Exit.html), for details.  For other completion options and features, see [Completion Options](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Options.html).

If you specify a buffer that does not exist, C-x b creates a new, empty buffer that is not visiting any file, and selects it for editing.  The default value of the variable `major-mode` determines the new buffer’s major mode; the default value is Fundamental mode.  See [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html).  One reason to create a new buffer is to use it for making temporary notes.  If you try to save it, Emacs asks for the file name to use, and the buffer’s major mode is re-established taking that file name into account (see [Choosing File Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Choosing-Modes.html)).



For conveniently switching between a few buffers, use the commands C-x LEFT and C-x RIGHT.  C-x LEFT (`previous-buffer`) selects the previous buffer (following the order of most recent selection in the current frame), while C-x RIGHT (`next-buffer`) moves through buffers in the reverse direction.  Both commands support a numeric prefix argument that serves as a repeat count.



To select a buffer in a window other than the current one (see [Multiple Windows](https://www.gnu.org/software/emacs/manual/html_node/emacs/Windows.html)), type C-x 4 b (`switch-to-buffer-other-window`).  This prompts for a buffer name using the minibuffer, displays that buffer in another window, and selects that window.



Similarly, C-x 5 b (`switch-to-buffer-other-frame`) prompts for a buffer name, displays that buffer in another frame (see [Frames and Graphical Displays](https://www.gnu.org/software/emacs/manual/html_node/emacs/Frames.html)), and selects that frame.  If the buffer is already being shown in a window on another frame, Emacs selects that window and frame instead of creating a new frame.

See [Displaying a Buffer in a Window](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Buffers.html), for how the C-x 4 b and C-x 5 b commands get the window and/or frame to display in.

In addition, C-x C-f, and any other command for visiting a file, can also be used to switch to an existing file-visiting buffer. See [Visiting Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visiting.html).



C-u M-g M-g, that is `goto-line` with a plain prefix argument, reads a number n using the minibuffer, selects the most recently selected buffer other than the current buffer in another window, and then moves point to the beginning of line number n in that buffer.  This is mainly useful in a buffer that refers to line numbers in another buffer: if point is on or just after a number, `goto-line` uses that number as the default for n.  Note that prefix arguments other than just C-u behave differently. C-u 4 M-g M-g goes to line 4 in the *current* buffer, without reading a number from the minibuffer.  (Remember that M-g M-g without prefix argument reads a number n and then moves to line number n in the current buffer.  See [Changing the Location of Point](https://www.gnu.org/software/emacs/manual/html_node/emacs/Moving-Point.html).)

Emacs uses buffer names that start with a space for internal purposes. It treats these buffers specially in minor ways—for example, by default they do not record undo information.  It is best to avoid using such buffer names yourself.



### 21.2 Listing Existing Buffers

- C-x C-b

  List the existing buffers (`list-buffers`).



To display a list of existing buffers, type C-x C-b.  This pops up a buffer menu in a buffer named *Buffer List*.  Each line in the list shows one buffer’s name, size, major mode and visited file. The buffers are listed in the order that they were current; the buffers that were current most recently come first.  This section describes how the list of buffers is displayed and how to interpret the various indications in the list; see [Operating on Several Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Several-Buffers.html), for description of the special mode in the *Buffer List* buffer and the commands available there.

‘.’ in the first field of a line indicates that the buffer is current.  ‘%’ indicates a read-only buffer.  ‘*’ indicates that the buffer is modified.  If several buffers are modified, it may be time to save some with C-x s (see [Commands for Saving Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Save-Commands.html)). Here is an example of a buffer list:

```
CRM Buffer                Size  Mode              File
. * .emacs                3294  ELisp/l           ~/.emacs
 %  *Help*                 101  Help
    search.c             86055  C                 ~/cvs/emacs/src/search.c
 %  src                  20959  Dired by name     ~/cvs/emacs/src/
  * *mail*                  42  Mail
 %  HELLO                 1607  Fundamental       ~/cvs/emacs/etc/HELLO
 %  NEWS                481184  Outline           ~/cvs/emacs/etc/NEWS
    *scratch*              191  Lisp Interaction
  * *Messages*            1554  Messages
```

The buffer *Help* was made by a help request (see [Help](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help.html)); it is not visiting any file.  The buffer `src` was made by Dired on the directory ~/cvs/emacs/src/.  You can list only buffers that are visiting files by giving the command a prefix argument, as in C-u C-x C-b.

By default, `list-buffers` omits buffers whose names begin with a space, unless they visit files: such buffers are used internally by Emacs (but the I command countermands that, see [Operating on Several Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Several-Buffers.html)).



### 21.3 Miscellaneous Buffer Operations

- C-x C-q

  Toggle read-only status of buffer (`read-only-mode`).

- C-x x r RET buffer RET

  Change the name of the current buffer.

- C-x x u

  Rename the current buffer by adding ‘<number>’ to the end.

- M-x view-buffer RET buffer RET

  Scroll through buffer buffer.  See [View Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/View-Mode.html).



A buffer can be *read-only*, which means that commands to insert or delete its text are not allowed.  (However, other commands, like C-x RET f, can still mark it as modified, see [Specifying a Coding System for File Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Coding.html)).  The mode line indicates read-only buffers with ‘%%’ or ‘%*’ near the left margin.  See [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html).  Read-only buffers are usually made by subsystems such as Dired and Rmail that have special commands to operate on the text.  Visiting a file whose access control says you cannot write it also makes the buffer read-only.



The command C-x C-q (`read-only-mode`) makes a read-only buffer writable, and makes a writable buffer read-only.  This works by setting the variable `buffer-read-only`, which has a local value in each buffer and makes the buffer read-only if its value is non-`nil`.  If you change the option `view-read-only` to a non-`nil` value, making the buffer read-only with C-x C-q also enables View mode in the buffer (see [View Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/View-Mode.html)).



C-x x r (`rename-buffer` changes the name of the current buffer.  You specify the new name as a minibuffer argument; there is no default.  If you specify a name that is in use for some other buffer, an error happens and no renaming is done.



C-x x u (`rename-uniquely`) renames the current buffer to a similar name with a numeric suffix added to make it both different and unique.  This command does not need an argument.  It is useful for creating multiple shell buffers: if you rename the *shell* buffer, then do M-x shell again, it makes a new shell buffer named *shell*; meanwhile, the old shell buffer continues to exist under its new name.  This method is also good for mail buffers, compilation buffers, and most Emacs features that create special buffers with particular names.  (With some of these features, such as M-x compile, M-x grep, you need to switch to some other buffer before using the command again, otherwise it will reuse the current buffer despite the name change.)



The commands M-x append-to-buffer and C-x x i (`insert-buffer`) can also be used to copy text from one buffer to another.  See [Accumulating Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Accumulating-Text.html).



### 21.4 Killing Buffers



If you continue an Emacs session for a while, you may accumulate a large number of buffers.  You may then find it convenient to *kill* the buffers you no longer need.  (Some other editors call this operation *close*, and talk about “closing the buffer” or “closing the file” visited in the buffer.)  On most operating systems, killing a buffer releases the memory Emacs used for the buffer back to the operating system so that other programs can use it.  Here are some commands for killing buffers:

- C-x k buffer RET

  Kill buffer buffer (`kill-buffer`).

- M-x kill-some-buffers

  Offer to kill each buffer, one by one.

- M-x kill-matching-buffers

  Offer to kill all buffers matching a regular expression.

- M-x kill-matching-buffers-no-ask

  Like `kill-matching-buffers`, but don’t ask for confirmation.



C-x k (`kill-buffer`) kills one buffer, whose name you specify in the minibuffer.  The default, used if you type just RET in the minibuffer, is to kill the current buffer.  If you kill the current buffer, another buffer becomes current: one that was current in the recent past but is not displayed in any window now.  If you ask to kill a file-visiting buffer that is modified, then you must confirm with yes before the buffer is killed.



The command M-x kill-some-buffers asks about each buffer, one by one.  An answer of yes means to kill the buffer, just like `kill-buffer`.  This command ignores buffers whose names begin with a space, which are used internally by Emacs.



The command M-x kill-matching-buffers prompts for a regular expression and kills all buffers whose names match that expression. See [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html).  Like `kill-some-buffers`, it asks for confirmation before each kill.  This command normally ignores buffers whose names begin with a space, which are used internally by Emacs.  To kill internal buffers as well, call `kill-matching-buffers` with a prefix argument.  The command M-x kill-matching-buffers-no-ask works like `kill-matching-buffers`, but doesn’t ask for confirmation before killing each matching buffer.

The Buffer Menu feature is also convenient for killing various buffers.  See [Operating on Several Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Several-Buffers.html).



If you want to do something special every time a buffer is killed, you can add hook functions to the hook `kill-buffer-hook` (see [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html)).



If you run one Emacs session for a period of days, as many people do, it can fill up with buffers that you used several days ago.  The command M-x clean-buffer-list is a convenient way to purge them; it kills all the unmodified buffers that you have not used for a long time.  An ordinary buffer is killed if it has not been displayed for three days; however, you can specify certain buffers that should never be killed automatically, and others that should be killed if they have been unused for a mere hour.  These defaults, and other aspects of this command’s behavior, can be controlled by customizing several options described in the doc string of `clean-buffer-list`.



You can also have this buffer purging done for you, once a day, by enabling Midnight mode.  Midnight mode operates each day at midnight; at that time, it runs `clean-buffer-list`, or whichever functions you have placed in the normal hook `midnight-hook` (see [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html)).  To enable Midnight mode, use the Customization buffer to set the variable `midnight-mode` to `t`.  See [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html).



### 21.5 Operating on Several Buffers



- M-x buffer-menu

  Begin editing a buffer listing all Emacs buffers.

- M-x buffer-menu-other-window

  Similar, but do it in another window.

The *Buffer Menu* opened by C-x C-b (see [Listing Existing Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/List-Buffers.html)) does not merely list buffers.  It also allows you to perform various operations on buffers, through an interface similar to Dired (see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html)).  You can save buffers, kill them (here called *deleting* them, for consistency with Dired), or display them.



To use the Buffer Menu, type C-x C-b and switch to the window displaying the *Buffer List* buffer.  You can also type M-x buffer-menu to open the Buffer Menu in the selected window. Alternatively, the command M-x buffer-menu-other-window opens the Buffer Menu in another window, and selects that window.

The Buffer Menu is a read-only buffer, and can be changed only through the special commands described in this section.  The usual cursor motion commands can be used in this buffer.  The following commands apply to the buffer described on the current line:

- d

  Flag the buffer for deletion (killing), then move point to the next line (`Buffer-menu-delete`).  The deletion flag is indicated by the character ‘D’ on the line, before the buffer name.  The deletion occurs only when you type the x command (see below).

- C-d

  Like d, but move point up instead of down (`Buffer-menu-delete-backwards`).

- s

  Flag the buffer for saving (`Buffer-menu-save`).  The save flag is indicated by the character ‘S’ on the line, before the buffer name.  The saving occurs only when you type x.  You may request both saving and deletion for the same buffer.

- x

  Perform all flagged deletions and saves (`Buffer-menu-execute`).

- u

  Remove all flags from the current line, and move down (`Buffer-menu-unmark`).  With a prefix argument, moves up after removing the flags.

- DEL

  Move to the previous line and remove all flags on that line (`Buffer-menu-backup-unmark`).

- M-DEL

  Remove a particular flag from all lines (`Buffer-menu-unmark-all-buffers`).  This asks for a single character, and unmarks buffers marked with that character; typing RET removes all marks.

- U

  Remove all flags from all the lines (`Buffer-menu-unmark-all`).

The commands for removing flags, d and C-d, accept a numeric argument as a repeat count.

The following commands operate immediately on the buffer listed on the current line.  They also accept a numeric argument as a repeat count.

- ~

  Mark the buffer as unmodified (`Buffer-menu-not-modified`). See [Commands for Saving Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Save-Commands.html).

- %

  Toggle the buffer’s read-only status (`Buffer-menu-toggle-read-only`).  See [Miscellaneous Buffer Operations](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Buffer.html).

- t

  Visit the buffer as a tags table (`Buffer-menu-visit-tags-table`).  See [Selecting a Tags Table](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Tags-Table.html).

The following commands are used to select another buffer or buffers:

- q

  Quit the Buffer Menu (`quit-window`).  The most recent formerly visible buffer is displayed in its place.

- RET

- f

  Select this line’s buffer, replacing the *Buffer List* buffer in its window (`Buffer-menu-this-window`).

- o

  Select this line’s buffer in another window, as if by C-x 4 b, leaving *Buffer List* visible (`Buffer-menu-other-window`).

- C-o

  Display this line’s buffer in another window, without selecting it (`Buffer-menu-switch-other-window`).

- 1

  Select this line’s buffer in a full-frame window (`Buffer-menu-1-window`).

- 2

  Set up two windows on the current frame, with this line’s buffer selected in one, and a previously current buffer (aside from *Buffer List*) in the other (`Buffer-menu-2-window`).

- b

  Bury this line’s buffer (`Buffer-menu-bury`) (i.e., move it to the end of the buffer list).

- m

  Mark this line’s buffer to be displayed in another window if you exit with the v command (`Buffer-menu-mark`).  The display flag is indicated by the character ‘>’ at the beginning of the line. (A single buffer may not have both deletion and display flags.)

- v

  Select this line’s buffer, and also display in other windows any buffers flagged with the m command (`Buffer-menu-select`). If you have not flagged any buffers, this command is equivalent to 1.

The following commands affect the entire buffer list:

- S

  Sort the Buffer Menu entries according to their values in the column at point.  With a numeric prefix argument n, sort according to the n-th column (`tabulated-list-sort`).

- }

  Widen the current column width by n (the prefix numeric argument) characters.

- {

  Narrow the current column width by n (the prefix numeric argument) characters.

- T

  Delete, or reinsert, lines for non-file buffers (`Buffer-menu-toggle-files-only`).  This command toggles the inclusion of such buffers in the buffer list.

- I

  Toggle display of internal buffers, those whose names begin with a space.

Normally, the buffer *Buffer List* is not updated automatically when buffers are created and killed; its contents are just text.  If you have created, deleted or renamed buffers, the way to update *Buffer List* to show what you have done is to type g (`revert-buffer`).  You can make this happen regularly every `auto-revert-interval` seconds if you enable Auto Revert mode in this buffer, as long as it is not marked modified.  Global Auto Revert mode applies to the *Buffer List* buffer only if `global-auto-revert-non-file-buffers` is non-`nil`. See [global-auto-revert-non-file-buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Reverting-the-Buffer-Menu.html), for details.



### 21.6 Indirect Buffers



An *indirect buffer* shares the text of some other buffer, which is called the *base buffer* of the indirect buffer.  In some ways it is a buffer analogue of a symbolic link between files.

- M-x make-indirect-buffer RET base-buffer RET indirect-name RET

  Create an indirect buffer named indirect-name with base buffer base-buffer.

- M-x clone-indirect-buffer RET

  Create an indirect buffer that is a twin copy of the current buffer.

- C-x 4 c[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indirect-Buffers.html#index-C_002dx-4-c)

  Create an indirect buffer that is a twin copy of the current buffer, and select it in another window (`clone-indirect-buffer-other-window`).

The text of the indirect buffer is always identical to the text of its base buffer; changes made by editing either one are visible immediately in the other.  “Text” here includes both the characters and their text properties.  But in all other respects, the indirect buffer and its base buffer are completely separate.  They can have different names, different values of point, different narrowing, different markers, different overlays, different major modes, and different local variables.

An indirect buffer cannot visit a file, but its base buffer can.  If you try to save the indirect buffer, that actually works by saving the base buffer.  Killing the base buffer effectively kills the indirect buffer, but killing an indirect buffer has no effect on its base buffer.

One way to use indirect buffers is to display multiple views of an outline.  See [Viewing One Outline in Multiple Views](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Views.html).

A quick and handy way to make an indirect buffer is with the command C-x 4 c (`clone-indirect-buffer-other-window`).  It creates and selects an indirect buffer whose base buffer is the current buffer.  With a numeric argument, it prompts for the name of the indirect buffer; otherwise it uses the name of the current buffer, with a ‘<n>’ suffix added.

The more general way to make an indirect buffer is with the command M-x make-indirect-buffer.  It creates an indirect buffer named indirect-name from a buffer base-buffer, prompting for both using the minibuffer.



The functions that create indirect buffers run the hook `clone-indirect-buffer-hook` after creating the indirect buffer. When this hook runs, the newly created indirect buffer is the current buffer.

Note: When a modification is made to the text of a buffer, the modification hooks are run only in the base buffer, because most of the functions on those hooks are not prepared to work correctly in indirect buffers.  So if you need a modification hook function in an indirect buffer, you need to manually add that function to the hook *in the base buffer* and then make the function operate in the desired indirect buffer.



### 21.7 Convenience Features and Customization of Buffer Handling

This section describes several modes and features that make it more convenient to switch between buffers.



#### 21.7.1 Making Buffer Names Unique



When several buffers visit identically-named files, Emacs must give the buffers distinct names.  The default method adds a suffix based on the names of the directories that contain the files.  For example, if you visit files /foo/bar/mumble/name and /baz/quux/mumble/name at the same time, their buffers will be named ‘name<bar/mumble>’ and ‘name<quux/mumble>’, respectively. Emacs adds as many directory parts as are needed to make a unique name.



You can choose from several different styles for constructing unique buffer names, by customizing the option `uniquify-buffer-name-style`.

The `forward` naming method includes part of the file’s directory name at the beginning of the buffer name; using this method, buffers visiting the files /u/rms/tmp/Makefile and /usr/projects/zaphod/Makefile would be named ‘tmp/Makefile’ and ‘zaphod/Makefile’.

In contrast, the `post-forward` naming method would call the buffers ‘Makefile|tmp’ and ‘Makefile|zaphod’.  The default method `post-forward-angle-brackets` is like `post-forward`, except that it encloses the unique path in angle brackets.  The `reverse` naming method would call them ‘Makefile\tmp’ and ‘Makefile\zaphod’.  The nontrivial difference between `post-forward` and `reverse` occurs when just one directory name is not enough to distinguish two files; then `reverse` puts the directory names in reverse order, so that /top/middle/file becomes ‘file\middle\top’, while `post-forward` puts them in forward order after the file name, as in ‘file|top/middle’.  If `uniquify-buffer-name-style` is set to `nil`, the buffer names simply get ‘<2>’, ‘<3>’, etc. appended.

The value of `uniquify-buffer-name-style` can be set to a customized function with two arguments base and extra-strings where base is a string and extra-strings is a list of strings.  For example the current implementation for `post-forward-angle-brackets` could be:

```
(defun my-post-forward-angle-brackets (base extra-string)
  (concat base \"<\" (mapconcat #'identity extra-string \"/\") \">\"))
```

Which rule to follow for putting the directory names in the buffer name is not very important if you are going to *look* at the buffer names before you type one.  But as an experienced user, if you know the rule, you won’t have to look.  And then you may find that one rule or another is easier for you to remember and apply quickly.



#### 21.7.2 Fast minibuffer selection



Icomplete mode provides a convenient way to quickly select an element among the possible completions in a minibuffer.  When enabled, typing in the minibuffer continuously displays a list of possible completions that match the string you have typed.

At any time, you can type C-j to select the first completion in the list.  So the way to select a particular completion is to make it the first in the list.  There are two ways to do this.  You can type more of the completion name and thus narrow down the list, excluding unwanted completions above the desired one.  Alternatively, you can use C-. and C-, to rotate the list until the desired buffer is first.

M-TAB will select the first completion in the list, like C-j but without exiting the minibuffer, so you can edit it further.  This is typically used when entering a file name, where M-TAB can be used a few times to descend in the hierarchy of directories.

To enable Icomplete mode for the minibuffer, type M-x icomplete-mode, or customize the variable `icomplete-mode` to `t` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).

You can also additionally enable Icomplete mode for C-M-i (the command `completion-at-point`) by customizing the variable `icomplete-in-buffer` to `t`.  For in-buffer completion, the `completion-auto-help` variable controls when Icomplete mode’s display of possible completions appears.  The default value of `t` means that the display of possible completions appears when you first type C-M-i.

By default, when you press C-M-i, both Icomplete mode’s in-buffer display of possible completions and the *Completions* buffer appear.  If you are using `icomplete-in-buffer`, then you may wish to suppress this appearance of the *Completions* buffer.  To do that, add the following to your initialization file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(advice-add 'completion-at-point :after #'minibuffer-hide-completions)
```



An alternative to Icomplete mode is Fido mode.  This is very similar to Icomplete mode, but retains some functionality from a popular extension called Ido mode (in fact the name is derived from “Fake Ido”).  Among other things, in Fido mode, C-s and C-r are also used to rotate the completions list, C-k can be used to delete files and kill buffers in-list.  Another noteworthy aspect is that `flex` is used as the default completion style (see [How Completion Alternatives Are Chosen](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion-Styles.html)).  To change this, add the following to your initialization file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(defun my-icomplete-styles ()
  (setq-local completion-styles '(initials flex)))
(add-hook 'icomplete-minibuffer-setup-hook 'my-icomplete-styles)
```

To enable Fido mode, type M-x fido-mode, or customize the variable `fido-mode` to `t` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).



Icomplete mode and Fido mode display the possible completions on the same line as the prompt by default.  To display the completion candidates vertically under the prompt, type M-x icomplete-vertical-mode, or customize the variable `icomplete-vertical-mode` to `t` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).



#### 21.7.3 Customizing Buffer Menus



- M-x bs-show

  Make a list of buffers similarly to M-x list-buffers but customizable.

- M-x ibuffer

  Make a list of buffers and operate on them in Dired-like fashion.



M-x bs-show pops up a buffer list similar to the one normally displayed by C-x C-b, but whose display you can customize in a more flexible fashion.  For example, you can specify the list of buffer attributes to show, the minimum and maximum width of buffer name column, a regexp for names of buffers that will never be shown and those which will always be shown, etc.  If you prefer this to the usual buffer list, you can bind this command to C-x C-b.  To customize this buffer list, use the `bs` Custom group (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)), or invoke bs-customize.



MSB global minor mode (“MSB” stands for “mouse select buffer”) provides a different and customizable mouse buffer menu which you may prefer.  It replaces the `mouse-buffer-menu` commands, normally bound to C-Down-mouse-1 and C-F10, with its own commands, and also modifies the menu-bar buffer menu.  You can customize the menu in the `msb` Custom group.



IBuffer is a major mode for viewing a list of buffers and operating on them in a way analogous to that of Dired (see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html)), including filtering, marking, sorting in various ways, and acting on buffers.



## 22 Multiple Windows



Emacs can split a frame into two or many windows.  Multiple windows can display parts of different buffers, or different parts of one buffer.  Multiple frames always imply multiple windows, because each frame has its own set of windows.  Each window belongs to one and only one frame.



### 22.1 Concepts of Emacs Windows

Each Emacs window displays one Emacs buffer at any time.  A single buffer may appear in more than one window; if it does, any changes in its text are displayed in all the windows where it appears.  But these windows can show different parts of the buffer, because each window has its own value of point.



At any time, one Emacs window is the *selected window*; the buffer this window is displaying is the current buffer.  On graphical displays, the point is indicated by a solid blinking cursor in the selected window, and by a hollow box in non-selected windows.  On text terminals, the cursor is drawn only in the selected window. See [Displaying the Cursor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Cursor-Display.html).

Commands to move point affect the value of point for the selected Emacs window only.  They do not change the value of point in other Emacs windows, even those showing the same buffer.  The same is true for buffer-switching commands such as C-x b; they do not affect other windows at all.  However, there are other commands such as C-x 4 b that select a different window and switch buffers in it. Also, all commands that display information in a window, including (for example) C-h f (`describe-function`) and C-x C-b (`list-buffers`), usually work by displaying buffers in a nonselected window without affecting the selected window.

When multiple windows show the same buffer, they can have different regions, because they can have different values of point.  However, they all have the same value for the mark, because each buffer has only one mark position.

Each window has its own mode line, which displays the buffer name, modification status and major and minor modes of the buffer that is displayed in the window.  The selected window’s mode line appears in a different color.  See [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html), for details.

### 22.2 Splitting Windows

- C-x 2

  Split the selected window into two windows, one above the other (`split-window-below`).

- C-x 3

  Split the selected window into two windows, positioned side by side (`split-window-right`).

- C-mouse-2

  In the mode line of a window, split that window.



C-x 2 (`split-window-below`) splits the selected window into two windows, one above the other.  After splitting, the selected window is the upper one, and the newly split-off window is below. Both windows have the same value of point as before, and display the same portion of the buffer (or as close to it as possible).  If necessary, the windows are scrolled to keep point on-screen.  By default, the two windows each get half the height of the original window.  A positive numeric argument specifies how many lines to give to the top window; a negative numeric argument specifies how many lines to give to the bottom window.



If you change the variable `split-window-keep-point` to `nil`, C-x 2 instead adjusts the portion of the buffer displayed by the two windows, as well as the value of point in each window, in order to keep the text on the screen as close as possible to what it was before; furthermore, if point was in the lower half of the original window, the bottom window is selected instead of the upper one.



C-x 3 (`split-window-right`) splits the selected window into two side-by-side windows.  The left window is the selected one; the right window displays the same portion of the same buffer, and has the same value of point.  A positive numeric argument specifies how many columns to give the left window; a negative numeric argument specifies how many columns to give the right window.



When you split a window with C-x 3, each resulting window occupies less than the full width of the frame.  If it becomes too narrow, the buffer may be difficult to read if continuation lines are in use (see [Continuation Lines](https://www.gnu.org/software/emacs/manual/html_node/emacs/Continuation-Lines.html)).  Therefore, Emacs automatically switches to line truncation if the window width becomes narrower than 50 columns.  This truncation occurs regardless of the value of the variable `truncate-lines` (see [Line Truncation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Line-Truncation.html)); it is instead controlled by the variable `truncate-partial-width-windows`.  If the value of this variable is a positive integer (the default is 50), that specifies the minimum total width for a partial-width window before automatic line truncation occurs; if the value is `nil`, automatic line truncation is disabled; and for any other non-`nil` value, Emacs truncates lines in every partial-width window regardless of its width. The total width of a window is in column units as reported by `window-total-width` (see [Window Sizes](https://www.gnu.org/software/emacs/manual/html_node/elisp/Window-Sizes.html#Window-Sizes) in The Emacs Lisp Reference Manual), it includes the fringes, the continuation and truncation glyphs, the margins, and the scroll bar.

On text terminals, side-by-side windows are separated by a vertical divider which is drawn using the `vertical-border` face.



If you click C-mouse-2 in the mode line of a window, that splits the window, putting a vertical divider where you click. Depending on how Emacs is compiled, you can also split a window by clicking C-mouse-2 in the scroll bar, which puts a horizontal divider where you click (this feature does not work when Emacs uses GTK+ scroll bars).



By default, when you split a window, Emacs gives each of the resulting windows dimensions that are an integral multiple of the default font size of the frame.  That might subdivide the screen estate unevenly between the resulting windows.  If you set the variable `window-resize-pixelwise` to a non-`nil` value, Emacs will give each window the same number of pixels (give or take one pixel if the initial dimension was an odd number of pixels).  Note that when a frame’s pixel size is not a multiple of the frame’s character size, at least one window may get resized pixelwise even if this option is `nil`.



### 22.3 Using Other Windows

- C-x o

  Select another window (`other-window`).

- C-M-v

  Scroll the next window upward (`scroll-other-window`).

- C-M-S-v

  Scroll the next window downward (`scroll-other-window-down`).

- C-M-S-l

  Recenter the next window (`recenter-other-window`).

- mouse-1

  mouse-1, in the text area of a window, selects the window and moves point to the position clicked.  Clicking in the mode line selects the window without moving point in it.



With the keyboard, you can switch windows by typing C-x o (`other-window`).  That is an o, for “other”, not a zero. When there are more than two windows, this command moves through all the windows in a cyclic order, generally top to bottom and left to right. After the rightmost and bottommost window, it goes back to the one at the upper left corner.  A numeric argument means to move several steps in the cyclic order of windows.  A negative argument moves around the cycle in the opposite order.  When the minibuffer is active, the minibuffer window is the last window in the cycle; you can switch from the minibuffer window to one of the other windows, and later switch back and finish supplying the minibuffer argument that is requested. See [Editing in the Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-Edit.html).



The `other-window` command will normally only switch to the next window in the current frame (unless otherwise configured).  If you work in a multi-frame environment and you want windows in all frames to be part of the cycle, you can rebind C-x o to the `next-window-any-frame` command.  (See [Changing Key Bindings Interactively](https://www.gnu.org/software/emacs/manual/html_node/emacs/Rebinding.html), for how to rebind a command.)



The usual scrolling commands (see [Controlling the Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display.html)) apply to the selected window only, but there are also commands to scroll the next window. C-M-v (`scroll-other-window`) scrolls the window that C-x o would select.  In other respects, the command behaves like C-v; both move the buffer text upward relative to the window, and take positive and negative arguments.  (In the minibuffer, C-M-v scrolls the help window associated with the minibuffer, if any, rather than the next window in the standard cyclic order; see [Editing in the Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer-Edit.html).)  C-M-S-v (`scroll-other-window-down`) scrolls the next window downward in a similar way.  Likewise, C-M-S-l (`recenter-other-window`) behaves like C-l (`recenter-top-bottom`) in the next window.



If you set `mouse-autoselect-window` to a non-`nil` value, moving the mouse over a different window selects that window.  This feature is off by default.



### 22.4 Displaying in Another Window



C-x 4 is a prefix key for a variety of commands that switch to a buffer in a different window—either another existing window, or a new window created by splitting the selected window.  See [How `display-buffer` works](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Choice.html), for how Emacs picks or creates the window to use.

- C-x 4 b bufname RET

  Select buffer bufname in another window (`switch-to-buffer-other-window`).  See [Creating and Selecting Buffers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Select-Buffer.html).

- C-x 4 C-o bufname RET

  Display buffer bufname in some window, without trying to select it (`display-buffer`).  See [Displaying a Buffer in a Window](https://www.gnu.org/software/emacs/manual/html_node/emacs/Displaying-Buffers.html), for details about how the window is chosen.

- C-x 4 f filename RET

  Visit file filename and select its buffer in another window (`find-file-other-window`).  See [Visiting Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visiting.html).

- C-x 4 d directory RET

  Select a Dired buffer for directory directory in another window (`dired-other-window`).  See [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html).

- C-x 4 m

  Start composing a mail message, similar to C-x m (see [Sending Mail](https://www.gnu.org/software/emacs/manual/html_node/emacs/Sending-Mail.html)), but in another window (`compose-mail-other-window`).

- C-x 4 .

  Find the definition of an identifier, similar to M-. (see [Find Identifier References](https://www.gnu.org/software/emacs/manual/html_node/emacs/Xref.html)), but in another window (`xref-find-definitions-other-window`).

- C-x 4 r filename RET

  Visit file filename read-only, and select its buffer in another window (`find-file-read-only-other-window`).  See [Visiting Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visiting.html).

- C-x 4 4

  A more general prefix command affects the buffer displayed by a subsequent command invoked after this prefix command (`other-window-prefix`).  It requests the buffer displayed by a subsequent command to be shown in another window.

- C-x 4 1

  This general prefix command requests the buffer of the next command to be displayed in the same window.

### 22.5 Deleting and Resizing Windows



- C-x 0

  Delete the selected window (`delete-window`).

- C-x 1

  Delete all windows in the selected frame except the selected window (`delete-other-windows`).

- C-x 4 0

  Delete the selected window and kill the buffer that was showing in it (`kill-buffer-and-window`).  The last character in this key sequence is a zero.

- C-x w 0 RET buffer RET

  Delete windows showing the specified buffer.

- C-x ^

  Make selected window taller (`enlarge-window`).

- C-x }

  Make selected window wider (`enlarge-window-horizontally`).

- C-x {

  Make selected window narrower (`shrink-window-horizontally`).

- C-x -

  Shrink this window if its buffer doesn’t need so many lines (`shrink-window-if-larger-than-buffer`).

- C-x +

  Balance the sizes of all the windows of the selected frame (`balance-windows`).



To delete the selected window, type C-x 0 (`delete-window`).  (That is a zero.)  Once a window is deleted, the space that it occupied is given to an adjacent window (but not the minibuffer window, even if that is active at the time).  Deleting the window has no effect on the buffer it used to display; the buffer continues to exist, and you can still switch to it with C-x b. The option `delete-window-choose-selected` controls which window is chosen as the new selected window instead (see [Deleting Windows](https://www.gnu.org/software/emacs/manual/html_node/elisp/Deleting-Windows.html#Deleting-Windows) in The Emacs Lisp Reference Manual).



C-x 4 0 (`kill-buffer-and-window`) is a stronger command than C-x 0; it kills the current buffer and then deletes the selected window.



C-x 1 (`delete-other-windows`) deletes all the windows, *except* the selected one; the selected window expands to use the whole frame.  (This command cannot be used while the minibuffer window is active; attempting to do so signals an error.)

M-x delete-windows-on deletes windows that show a specific buffer.  It prompts for the buffer, defaulting to the current buffer. With prefix argument of zero, C-u 0, this command deletes windows only on the current display’s frames.



The command C-x ^ (`enlarge-window`) makes the selected window one line taller, taking space from a vertically adjacent window without changing the height of the frame.  With a positive numeric argument, this command increases the window height by that many lines; with a negative argument, it reduces the height by that many lines. If there are no vertically adjacent windows (i.e., the window is at the full frame height), that signals an error.  The command also signals an error if you attempt to reduce the height of any window below a certain minimum number of lines, specified by the variable `window-min-height` (the default is 4).



Similarly, C-x } (`enlarge-window-horizontally`) makes the selected window wider, and C-x { (`shrink-window-horizontally`) makes it narrower.  These commands signal an error if you attempt to reduce the width of any window below a certain minimum number of columns, specified by the variable `window-min-width` (the default is 10).

Mouse clicks on the mode line (see [Mode Line Mouse Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line-Mouse.html)) or on window dividers (see [Window Dividers](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Dividers.html)) provide another way to change window heights and to split or delete windows.



C-x - (`shrink-window-if-larger-than-buffer`) reduces the height of the selected window, if it is taller than necessary to show the whole text of the buffer it is displaying.  It gives the extra lines to other windows in the frame.



You can also use C-x + (`balance-windows`) to balance the sizes of all the windows of the selected frame (with the exception of the minibuffer window, see [The Minibuffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minibuffer.html)).  This command makes each horizontal pair of adjacent windows the same height, and each vertical pair of adjacent windows the same width.



### 22.6 Displaying a Buffer in a Window

It is a common Emacs operation to display or pop up some buffer in response to a user command.  There are several different ways in which commands do this.

Many commands, like C-x C-f (`find-file`), by default display the buffer by “taking over” the selected window, expecting that the user’s attention will be diverted to that buffer.

Some commands try to display intelligently, trying not to take over the selected window, e.g., by splitting off a new window and displaying the desired buffer there.  Such commands, which include the various help commands (see [Help](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help.html)), work by calling `display-buffer` internally.  See [How `display-buffer` works](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Choice.html), for details.

Other commands do the same as `display-buffer`, and additionally select the displaying window so that you can begin editing its buffer.  The command M-g M-n (`next-error`) is one example (see [Compilation Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Compilation-Mode.html)).  Such commands work by calling the function `pop-to-buffer` internally.  See [Switching to a Buffer in a Window](https://www.gnu.org/software/emacs/manual/html_node/elisp/Switching-Buffers.html#Switching-Buffers) in The Emacs Lisp Reference Manual.

Commands with names ending in `-other-window` behave like `display-buffer`, except that they never display in the selected window.  Several of these commands are bound in the C-x 4 prefix key (see [Displaying in Another Window](https://www.gnu.org/software/emacs/manual/html_node/emacs/Pop-Up-Window.html)).

Commands with names ending in `-other-frame` behave like `display-buffer`, except that they (i) never display in the selected window and (ii) prefer to either create a new frame or use a window on some other frame to display the desired buffer.  Several of these commands are bound in the C-x 5 prefix key.



Sometimes, a window is “dedicated” to its current buffer. See [Dedicated Windows](https://www.gnu.org/software/emacs/manual/html_node/elisp/Dedicated-Windows.html#Dedicated-Windows) in The Emacs Lisp Reference Manual. `display-buffer` will avoid reusing dedicated windows most of the time.  This is indicated by a ‘d’ in the mode line (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)).  A window can also be strongly dedicated, which prevents any changes to the buffer displayed in the window.  This is indicated by a ‘D’ in the mode line.

Usually, dedicated windows are used to display specialized buffers, but dedication can sometimes be useful interactively.  For example, when viewing errors with M-g M-n `next-error`, newly displayed source code may replace a buffer you want to refer to.  If you dedicate a window to that buffer, the command (through `display-buffer`) will prefer to use a different window instead.



You can use the command C-x w d (`toggle-window-dedicated`) to toggle whether the selected window is dedicated to the current buffer.  With a prefix argument, it makes the window strongly dedicated.



#### 22.6.1 How `display-buffer` works



The `display-buffer` command (as well as commands that call it internally) chooses a window to display by following the steps given below.  See [Choosing a Window for Displaying a Buffer](https://www.gnu.org/software/emacs/manual/html_node/elisp/Choosing-Window.html#Choosing-Window) in The Emacs Lisp Reference Manual, for details about how to alter this sequence of steps.

- If the buffer should be displayed in the selected window regardless of other considerations, reuse the selected window.  By default, this step is skipped, but you can tell Emacs not to skip it by adding a regular expression matching the buffer’s name together with a reference to the 

  ```
  display-buffer-same-window
  ```

   action function (see 

  Action Functions for Buffer Display

   in 

  The Emacs Lisp Reference Manual

  ) to the option

  ```
  display-buffer-alist
  ```

   (see 

  Choosing a Window for Displaying a Buffer

   in 

  The Emacs Lisp Reference Manual

  ). For example, to display the buffer 

  *scratch*

   preferably in the selected window write:

  ```
  (setopt
   display-buffer-alist
   '(("\\*scratch\\*" (display-buffer-same-window))))
  ```

  By default, `display-buffer-alist` is `nil`.

- Otherwise, if the buffer is already displayed in an existing window, reuse that window.  Normally, only windows on the selected frame are considered, but windows on other frames are also reusable if you use the corresponding `reusable-frames` action alist entry (see [Action Alists for Buffer Display](https://www.gnu.org/software/emacs/manual/html_node/elisp/Buffer-Display-Action-Alists.html#Buffer-Display-Action-Alists) in The Emacs Lisp Reference Manual).  See the next step for an example of how to do that.

- Otherwise, optionally create a new frame and display the buffer there. By default, this step is skipped.  To enable it, change the value of the option 

  ```
  display-buffer-base-action
  ```

   (see 

  Choosing a Window for Displaying a Buffer

   in 

  The Emacs Lisp Reference Manual

  ) as follows:

  ```
  (setopt
   display-buffer-base-action
   '((display-buffer-reuse-window display-buffer-pop-up-frame)
     (reusable-frames . 0)))
  ```

  This customization will also try to make the preceding step search for a reusable window on all visible or iconified frames.

- Otherwise, try to create a new window by splitting a window on the selected frame, and display the buffer in that new window.

  

  

  The split can be either vertical or horizontal, depending on the variables `split-height-threshold` and `split-width-threshold`.  These variables should have integer values.  If `split-height-threshold` is smaller than the chosen window’s height, the split puts the new window below.  Otherwise, if `split-width-threshold` is smaller than the window’s width, the split puts the new window on the right.  If neither condition holds, Emacs tries to split so that the new window is below—but only if the window was not split before (to avoid excessive splitting).

- Otherwise, display the buffer in a window previously showing it. Normally, only windows on the selected frame are considered, but with a suitable `reusable-frames` action alist entry (see above) the window may be also on another frame.

- Otherwise, display the buffer in an existing window on the selected frame.

- If all the above methods fail for whatever reason, create a new frame and display the buffer there.

#### 22.6.2 Displaying non-editable buffers.



Some buffers are shown in windows for perusal rather than for editing. Help commands (see [Help](https://www.gnu.org/software/emacs/manual/html_node/emacs/Help.html)) typically use a buffer called *Help* for that purpose, minibuffer completion (see [Completion](https://www.gnu.org/software/emacs/manual/html_node/emacs/Completion.html)) uses a buffer called *Completions*, etc.  Such buffers are usually displayed only for a short period of time.

Normally, Emacs chooses the window for such temporary displays via `display-buffer`, as described in the previous subsection.  The *Completions* buffer, on the other hand, is normally displayed in a window at the bottom of the selected frame, regardless of the number of windows already shown on that frame.

If you prefer Emacs to display a temporary buffer in a different fashion, customize the variable `display-buffer-alist` (see [Choosing a Window for Displaying a Buffer](https://www.gnu.org/software/emacs/manual/html_node/elisp/Choosing-Window.html#Choosing-Window) in The Emacs Lisp Reference Manual) appropriately.  For example, to display *Completions* always below the selected window, use the following form in your initialization file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(setopt
 display-buffer-alist
 '(("\\*Completions\\*" display-buffer-below-selected)))
```



The *Completions* buffer is also special in the sense that Emacs usually tries to make its window just as large as necessary to display all of its contents.  To resize windows showing other temporary displays, like, for example, the *Help* buffer, turn on the minor mode (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)) `temp-buffer-resize-mode` (see [Temporary Displays](https://www.gnu.org/software/emacs/manual/html_node/elisp/Temporary-Displays.html#Temporary-Displays) in The Emacs Lisp Reference Manual).



The maximum size of windows resized by `temp-buffer-resize-mode` can be controlled by customizing the options `temp-buffer-max-height` and `temp-buffer-max-width` (see [Temporary Displays](https://www.gnu.org/software/emacs/manual/html_node/elisp/Temporary-Displays.html#Temporary-Displays) in The Emacs Lisp Reference Manual), and cannot exceed the size of the containing frame.



Buffers showing warnings (such as byte-compilation warnings, see [Byte Compilation Functions](https://www.gnu.org/software/emacs/manual/html_node/elisp/Compilation-Functions.html#Compilation-Functions) in The Emacs Lisp Reference Manual) are also by default shown in a window at the bottom of the selected frame.  You can control this using the variable `warning-display-at-bottom`: if set to `nil`, Emacs will use the normal logic of `display-buffer` (see [How `display-buffer` works](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Choice.html)) instead, and you can customize that via `display-buffer-alist`.



### 22.7 Convenience Features for Window Handling



Winner mode is a global minor mode that records the changes in the window configuration (i.e., how the frames are partitioned into windows), so that you can undo them.  You can toggle Winner mode with M-x winner-mode, or by customizing the variable `winner-mode`.  When the mode is enabled, C-c left (`winner-undo`) undoes the last window configuration change.  If you change your mind while undoing, you can redo the changes you had undone using C-c right (`M-x winner-redo`).  To prevent Winner mode from binding C-c left and C-c right, you can customize the variable `winner-dont-bind-my-keys` to a non-`nil` value.  By default, Winner mode stores a maximum of 200 window configurations per frame, but you can change that by modifying the variable `winner-ring-size`.  If there are some buffers whose windows you wouldn’t want Winner mode to restore, add their names to the list variable `winner-boring-buffers` or to the regexp `winner-boring-buffers-regexp`.

Follow mode (M-x follow-mode) synchronizes several windows on the same buffer so that they always display adjacent sections of that buffer.  See [Follow Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Follow-Mode.html).



The Windmove package defines commands for moving directionally between neighboring windows in a frame.  M-x windmove-right selects the window immediately to the right of the currently selected one, and similarly for the left, up, and down counterparts. `windmove-default-keybindings` binds these commands to S-right etc.; doing so disables shift selection for those keys (see [Shift Selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shift-Selection.html)).  In the same way as key bindings can be defined for commands that select windows directionally, you can use `windmove-display-default-keybindings` to define keybindings for commands that specify in what direction to display the window for the buffer that the next command is going to display.  Also there is `windmove-delete-default-keybindings` to define keybindings for commands that delete windows directionally, and `windmove-swap-states-default-keybindings` that defines key bindings for commands that swap the window contents of the selected window with the window in the specified direction.

The command M-x compare-windows lets you compare the text shown in different windows.  See [Comparing Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Comparing-Files.html).



Scroll All mode (M-x scroll-all-mode) is a global minor mode that causes scrolling commands and point motion commands to apply to every single window.



### 22.8 Window Tab Line



The command `global-tab-line-mode` toggles the display of a *tab line* on the top screen line of each window.  The Tab Line shows special buttons (“tabs”) for each buffer that was displayed in a window, and allows switching to any of these buffers by clicking the corresponding button.  Clicking on the + icon adds a new buffer to the window-local tab line of buffers, and clicking on the x icon of a tab deletes it.  The mouse wheel on the tab line scrolls the tabs horizontally.

Touch screen input (see [Touchscreen Input and Virtual Keyboards](https://www.gnu.org/software/emacs/manual/html_node/emacs/Other-Input.html)) can also be used to interact with the “tab line”.  Long-pressing (see [Using Emacs on Touchscreens](https://www.gnu.org/software/emacs/manual/html_node/emacs/Touchscreens.html)) a tab will display a context menu with items that operate on the tab that was pressed; tapping a tab itself will result in switching to that tab’s buffer, and tapping a button on the tab line will behave as if it was clicked with mouse-1.

Selecting the previous window-local tab is the same as typing C-x LEFT (`previous-buffer`), selecting the next tab is the same as C-x RIGHT (`next-buffer`).  Both commands support a numeric prefix argument as a repeat count.

You can customize the variable `tab-line-tabs-function` to define the preferred contents of the tab line.  By default, it displays all buffers previously visited in the window, as described above.  But you can also set it to display a list of buffers with the same major mode as the current buffer, or to display buffers grouped by their major mode, where clicking on the mode name in the first tab displays a list of all major modes where you can select another group of buffers.

Note that the Tab Line is different from the Tab Bar (see [Tab Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Bars.html)). Whereas tabs on the Tab Bar at the top of each frame are used to switch between window configurations containing several windows with buffers, tabs on the Tab Line at the top of each window are used to switch between buffers in the window.

Also note that the tab line displays in the same space as the window tool bar, so only one of them can be displayed at any given time, unless you customize the value of `tab-line-format` in Lisp to add `(:eval (tab-line-format))` to `tab-line-format`. See [Mode Line Format](https://www.gnu.org/software/emacs/manual/html_node/elisp/Mode-Line-Format.html#Mode-Line-Format) in The Emacs Lisp Reference Manual.



### 22.9 Window Tool Bar



The command `global-window-tool-bar-mode` toggles the display of a tool bar at the top of each window.  When enabled, multiple windows can display their own tool bar simultaneously.  To conserve space, a window tool bar is hidden if there are no buttons to show, i.e. if `tool-bar-map` is `nil`.



If you want to toggle the display of a window tool bar for only some buffers, run the command `window-tool-bar-mode` in those buffers. This is useful to put in a mode hook.  For example, if you want the window tool bar to appear only for buffers that do not represent files and have a custom tool bar, you could add the following code to your init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(add-hook 'special-mode-hook 'window-tool-bar-mode)
```

Emacs can also display a single tool bar at the top of frames (see [Tool Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tool-Bars.html)).

Note that the window tool bar displays in the same space as the tab line, so only one of them can be displayed at any given time, unless you customize the value of `tab-line-format` to add `(:eval (window-tool-bar-string))` to `tab-line-format`.  See [Mode Line Format](https://www.gnu.org/software/emacs/manual/html_node/elisp/Mode-Line-Format.html#Mode-Line-Format) in The Emacs Lisp Reference Manual.

## 23 Frames and Graphical Displays

## 24 International Character Set Support



## 25 Major and Minor Modes

Emacs contains many *editing modes* that alter its basic behavior in useful ways.  These are divided into *major modes* and *minor modes*.

Major modes provide specialized facilities for working on a particular file type, such as a C source file (see [Editing Programs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Programs.html)), or a particular type of non-file buffer, such as a shell buffer (see [Running Shell Commands from Emacs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Shell.html)).  Major modes are mutually exclusive; each buffer has one and only one major mode at any time.

Minor modes are optional features which you can turn on or off, not necessarily specific to a type of file or buffer.  For example, Auto Fill mode is a minor mode in which SPC breaks lines between words as you type (see [Auto Fill Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Fill.html)).  Minor modes are independent of one another, and of the selected major mode.



### 25.1 Major Modes



Every buffer possesses a major mode, which determines the editing behavior of Emacs while that buffer is current.  The mode line normally shows the name of the current major mode, in parentheses (see [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html)).

The least specialized major mode is called *Fundamental mode*. This mode has no mode-specific redefinitions or variable settings, so that each Emacs command behaves in its most general manner, and each user option variable is in its default state.

For editing text of a specific type that Emacs knows about, such as Lisp code or English text, you typically use a more specialized major mode, such as Lisp mode or Text mode.  Most major modes fall into three major groups.  The first group contains modes for normal text, either plain or with mark-up.  It includes Text mode, HTML mode, SGML mode, TeX mode and Outline mode.  The second group contains modes for specific programming languages.  These include Lisp mode (which has several variants), C mode, Fortran mode, and others.  The third group consists of major modes that are not associated directly with files; they are used in buffers created for specific purposes by Emacs.  Examples include Dired mode for buffers made by Dired (see [Dired, the Directory Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/Dired.html)), Message mode for buffers made by C-x m (see [Sending Mail](https://www.gnu.org/software/emacs/manual/html_node/emacs/Sending-Mail.html)), and Shell mode for buffers used to communicate with an inferior shell process (see [Interactive Subshell](https://www.gnu.org/software/emacs/manual/html_node/emacs/Interactive-Shell.html)).

Usually, the major mode is automatically set by Emacs, when you first visit a file or create a buffer (see [Choosing File Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Choosing-Modes.html)).  You can explicitly select a new major mode by using an M-x command. Take the name of the mode and add `-mode` to get the name of the command to select that mode (e.g., M-x lisp-mode enters Lisp mode).  Since every buffer has exactly one major mode, there is no way to “turn off” a major mode; instead you must switch to a different one.



The value of the buffer-local variable `major-mode` is a symbol with the same name as the major mode command (e.g., `lisp-mode`). This variable is set automatically; you should not change it yourself.

The default value of `major-mode` determines the major mode to use for files that do not specify a major mode, and for new buffers created with C-x b.  Normally, this default value is the symbol `fundamental-mode`, which specifies Fundamental mode.  You can change this default value via the Customization interface (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)), or by adding a line like this to your init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)):

```
(setq-default major-mode 'text-mode)
```

If the default value of `major-mode` is `nil`, the major mode is taken from the previously current buffer.

Specialized major modes often change the meanings of certain keys to do something more suitable for the mode.  For instance, programming language modes bind TAB to indent the current line according to the rules of the language (see [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html)).  The keys that are commonly changed are TAB, DEL, and C-j.  Many modes also define special commands of their own, usually bound to key sequences whose prefix key is C-c (see [Keys](https://www.gnu.org/software/emacs/manual/html_node/emacs/Keys.html)).  Major modes can also alter user options and variables; for instance, programming language modes typically set a buffer-local value for the variable `comment-start`, which determines how source code comments are delimited (see [Manipulating Comments](https://www.gnu.org/software/emacs/manual/html_node/emacs/Comments.html)).

To view the documentation for the current major mode, including a list of its key bindings, type C-h m (`describe-mode`). See [Other Help Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Misc-Help.html).



Every major mode, apart from Fundamental mode, defines a *mode hook*, a customizable list of Lisp functions to run each time the mode is enabled in a buffer.  See [Hooks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Hooks.html), for more information about hooks.  Each mode hook is named after its major mode, e.g., Fortran mode has `fortran-mode-hook`.  Furthermore, all text-based major modes run `text-mode-hook`, and many programming language modes [11](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html#FOOT11) (including all those distributed with Emacs) run `prog-mode-hook`, prior to running their own mode hooks.  Hook functions can look at the value of the variable `major-mode` to see which mode is actually being entered.

Mode hooks are commonly used to enable minor modes (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)).  For example, you can put the following lines in your init file to enable Flyspell minor mode in all text-based major modes (see [Checking and Correcting Spelling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Spelling.html)), and ElDoc minor mode in Emacs Lisp mode (see [Programming Language Documentation Lookup](https://www.gnu.org/software/emacs/manual/html_node/emacs/Programming-Language-Doc.html)):

```
(add-hook 'text-mode-hook 'flyspell-mode)
(add-hook 'emacs-lisp-mode-hook 'eldoc-mode)
```

> More specifically, the modes which are “derived” from `prog-mode` (see [Derived Modes](https://www.gnu.org/software/emacs/manual/html_node/elisp/Derived-Modes.html#Derived-Modes) in The Emacs Lisp Reference Manual).



### 25.2 Minor Modes



A minor mode is an optional editing mode that alters the behavior of Emacs in some well-defined way.  Unlike major modes, any number of minor modes can be in effect at any time.  Some minor modes are *buffer-local*, and can be turned on (enabled) in certain buffers and off (disabled) in others.  Other minor modes are *global*: while enabled, they affect everything you do in the Emacs session, in all buffers.  Most minor modes are disabled by default, but a few are enabled by default.

Most buffer-local minor modes say in the mode line when they are enabled, just after the major mode indicator.  For example, ‘Fill’ in the mode line means that Auto Fill mode is enabled. See [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html).



Like major modes, each minor mode is associated with a *mode command*, whose name consists of the mode name followed by ‘-mode’.  For instance, the mode command for Auto Fill mode is `auto-fill-mode`.  But unlike a major mode command, which simply enables the mode, the mode command for a minor mode can either enable or disable it:

- If you invoke the mode command directly with no prefix argument (either via M-x, or by binding it to a key and typing that key; see [Customizing Key Bindings](https://www.gnu.org/software/emacs/manual/html_node/emacs/Key-Bindings.html)), that *toggles* the minor mode.  The minor mode is turned on if it was off, and turned off if it was on.
- If you invoke the mode command with a prefix argument, the minor mode is unconditionally turned off if that argument is zero or negative; otherwise, it is unconditionally turned on.
- If the mode command is called via Lisp, the minor mode is unconditionally turned on if the argument is omitted or `nil`. This makes it easy to turn on a minor mode from a major mode’s mode hook (see [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html)).  A non-`nil` argument is handled like an interactive prefix argument, as described above.

Most minor modes also have a *mode variable*, with the same name as the mode command.  Its value is non-`nil` if the mode is enabled, and `nil` if it is disabled.  In general, you should not try to enable or disable the mode by changing the value of the mode variable directly in Lisp; you should run the mode command instead. However, setting the mode variable through the Customize interface (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)) will always properly enable or disable the mode, since Customize automatically runs the mode command for you.

The following is a list of some buffer-local minor modes:

- Abbrev mode automatically expands text based on pre-defined abbreviation definitions.  See [Abbrevs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Abbrevs.html).
- Auto Fill mode inserts newlines as you type to prevent lines from becoming too long.  See [Filling Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Filling.html).
- Auto Save mode saves the buffer contents periodically to reduce the amount of work you can lose in case of a crash.  See [Auto-Saving: Protection Against Disasters](https://www.gnu.org/software/emacs/manual/html_node/emacs/Auto-Save.html).
- Electric Quote mode automatically converts quotation marks.  For example, it requotes text typed `like this' to text `‘like this’`.  You can control what kind of text it operates in, and you can disable it entirely in individual buffers.  See [Quotation Marks](https://www.gnu.org/software/emacs/manual/html_node/emacs/Quotation-Marks.html).
- Enriched mode enables editing and saving of formatted text. See [Enriched Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Enriched-Text.html).
- Flyspell mode automatically highlights misspelled words. See [Checking and Correcting Spelling](https://www.gnu.org/software/emacs/manual/html_node/emacs/Spelling.html).
- Font-Lock mode automatically highlights certain textual units found in programs.  It is enabled globally by default, but you can disable it in individual buffers.  See [Text Faces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Faces.html).
- Display Line Numbers mode is a convenience wrapper around `display-line-numbers`, setting it using the value of `display-line-numbers-type`.  See [Customization of Display](https://www.gnu.org/software/emacs/manual/html_node/emacs/Display-Custom.html).
- Outline minor mode provides similar facilities to the major mode called Outline mode.  See [Outline Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Outline-Mode.html).
- Overwrite mode causes ordinary printing characters to replace existing text instead of shoving it to the right.  For example, if point is in front of the ‘B’ in ‘FOOBAR’, then in Overwrite mode typing a G changes it to ‘FOOGAR’, instead of producing ‘FOOGBAR’ as usual.  In Overwrite mode, the command C-q inserts the next character whatever it may be, even if it is a digit—this gives you a way to insert a character instead of replacing an existing character.  The mode command, `overwrite-mode`, is bound to the Insert key.
- Binary Overwrite mode is a variant of Overwrite mode for editing binary files; it treats newlines and tabs like other characters, so that they overwrite other characters and can be overwritten by them. In Binary Overwrite mode, digits after C-q specify an octal character code, as usual.
- Visual Line mode performs word wrapping, causing long lines to be wrapped at word boundaries.  See [Visual Line Mode](https://www.gnu.org/software/emacs/manual/html_node/emacs/Visual-Line-Mode.html).

And here are some useful global minor modes:

- Column Number mode enables display of the current column number in the mode line.  See [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html).
- Delete Selection mode causes text insertion to first delete the text in the region, if the region is active.  See [Operating on the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Using-Region.html).
- Icomplete mode displays an indication of available completions when you are in the minibuffer and completion is active.  See [Fast minibuffer selection](https://www.gnu.org/software/emacs/manual/html_node/emacs/Icomplete.html).
- Line Number mode enables display of the current line number in the mode line.  It is enabled by default.  See [The Mode Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mode-Line.html).
- Menu Bar mode gives each frame a menu bar.  It is enabled by default. See [Menu Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Menu-Bars.html).
- Scroll Bar mode gives each window a scroll bar.  It is enabled by default, but the scroll bar is only displayed on graphical terminals. See [Scroll Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Scroll-Bars.html).
- Tool Bar mode gives each frame a tool bar.  It is enabled by default, but the tool bar is only displayed on graphical terminals.  See [Tool Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tool-Bars.html).
- Window Tool Bar mode gives windows a tool bar.  See [Window Tool Bar](https://www.gnu.org/software/emacs/manual/html_node/emacs/Window-Tool-Bar.html).
- Tab Bar mode gives each frame a tab bar.  See [Tab Bars](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Bars.html).
- Tab Line mode gives each window a tab line.  See [Window Tab Line](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Line.html).
- Transient Mark mode highlights the region, and makes many Emacs commands operate on the region when the mark is active.  It is enabled by default.  See [The Mark and the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Mark.html).

### 25.3 Choosing File Modes



When you visit a file, Emacs chooses a major mode automatically. Normally, it makes the choice based on the file name—for example, files whose names end in ‘.c’ are normally edited in C mode—but sometimes it chooses the major mode based on special text in the file. This special text can also be used to enable buffer-local minor modes.

Here is the exact procedure:

First, Emacs checks whether the file contains file-local mode variables.  See [Local Variables in Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Variables.html).  If there is a file-local variable that specifies a major mode, then Emacs uses that major mode, ignoring all other criteria.  There are several methods to specify a major mode using a file-local variable; the simplest is to put the mode name in the first nonblank line, preceded and followed by ‘-*-’.  Other text may appear on the line as well.  For example,

```
; -*-Lisp-*-
```

tells Emacs to use Lisp mode.  Note how the semicolon is used to make Lisp treat this line as a comment.  You could equivalently write

```
; -*- mode: Lisp;-*-
```

You can also use file-local variables to specify buffer-local minor modes, by using `eval` specifications.  For example, this first nonblank line puts the buffer in Lisp mode and enables Auto-Fill mode:

```
; -*- mode: Lisp; eval: (auto-fill-mode 1); -*-
```

Note, however, that it is usually inappropriate to enable minor modes this way, since most minor modes represent individual user preferences.  If you personally want to use a minor mode for a particular file type, it is better to enable the minor mode via a major mode hook (see [Major Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Major-Modes.html)).

Second, Emacs checks whether the file’s extension matches an entry in any directory-local `auto-mode-alist`.  These are found using the .dir-locals.el facility (see [Per-Directory Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Directory-Variables.html)).



Third, if there is no file variable specifying a major mode, Emacs checks whether the file’s contents begin with ‘#!’.  If so, that indicates that the file can serve as an executable shell command, which works by running an interpreter named on the file’s first line (the rest of the file is used as input to the interpreter). Therefore, Emacs tries to use the interpreter name to choose a mode. For instance, a file that begins with ‘#!/usr/bin/perl’ is opened in Perl mode.  The variable `interpreter-mode-alist` specifies the correspondence between interpreter program names and major modes.

When the first line starts with ‘#!’, you usually cannot use the ‘-*-’ feature on the first line, because the system would get confused when running the interpreter.  So Emacs looks for ‘-*-’ on the second line in such files as well as on the first line.  The same is true for man pages which start with the magic string ‘'\"’ to specify a list of troff preprocessors.



Fourth, Emacs tries to determine the major mode by looking at the text at the start of the buffer, based on the variable `magic-mode-alist`.  By default, this variable is `nil` (an empty list), so Emacs skips this step; however, you can customize it in your init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html)).  The value should be a list of elements of the form

```
(regexp . mode-function)
```

where regexp is a regular expression (see [Syntax of Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexps.html)), and mode-function is a major mode command.  If the text at the beginning of the file matches regexp, Emacs chooses the major mode specified by mode-function.

Alternatively, an element of `magic-mode-alist` may have the form

```
(match-function . mode-function)
```

where match-function is a Lisp function that is called at the beginning of the buffer; if the function returns non-`nil`, Emacs set the major mode with mode-function.

Fifth—if Emacs still hasn’t found a suitable major mode—it looks at the file’s name.  The correspondence between file names and major modes is controlled by the variable `auto-mode-alist`.  Its value is a list in which each element has this form,

```
(regexp . mode-function)
```

or this form,

```
(regexp mode-function flag)
```

For example, one element normally found in the list has the form `("\\.c\\'" . c-mode)`, and it is responsible for selecting C mode for files whose names end in .c.  (Note that ‘\\’ is needed in Lisp syntax to include a ‘\’ in the string, which must be used to suppress the special meaning of ‘.’ in regexps.)



If the element has the form `(regexp mode-function flag)` and flag is non-`nil`, then after calling mode-function (if it is non-`nil`), Emacs discards the suffix that matched regexp and searches the list again for another match.  This “recursive extension stripping” is used for files which have multiple extensions, and the “outer” extension hides the “inner” one that actually specifies the right mode.  For example, backup files and GPG-encrypted files with .gpg extension use this feature.



On GNU/Linux and other systems with case-sensitive file names, Emacs performs a case-sensitive search through `auto-mode-alist`; if this search fails, it performs a second case-insensitive search through the alist.  To suppress the second search, change the variable `auto-mode-case-fold` to `nil`.  On systems with case-insensitive file names, such as Microsoft Windows, Emacs performs a single case-insensitive search through `auto-mode-alist`.



Finally, if Emacs *still* hasn’t found a major mode to use, it compares the text at the start of the buffer to the variable `magic-fallback-mode-alist`.  This variable works like `magic-mode-alist`, described above, except that it is consulted only *after* `auto-mode-alist`.  By default, `magic-fallback-mode-alist` contains forms that check for image files, HTML/XML/SGML files, PostScript files, and Unix style Conf files.



Once a major mode is found, Emacs does a final check to see if the mode has been *remapped* by `major-mode-remap-alist`, in which case it uses the remapped mode instead.  This is used when several different major modes can be used for the same file type, so you can specify which mode you prefer.  Note that this remapping affects the major mode found by all of the methods described above, so, for example, the mode specified by the first line of the file will not necessarily be the mode actually turned on in the buffer visiting the file.  (This remapping also affects `revert-buffer`, see [Reverting a Buffer](https://www.gnu.org/software/emacs/manual/html_node/emacs/Reverting.html).)  When several modes are available for the same file type, you can tell Emacs about your major-mode preferences by customizing `major-mode-remap-alist`.  For example, put this in your ~/.emacs init file (see [The Emacs Initialization File](https://www.gnu.org/software/emacs/manual/html_node/emacs/Init-File.html))

```
  (add-to-list 'major-mode-remap-alist '(c-mode . c-ts-mode))
```

to force Emacs to invoke `c-ts-mode` when `c-mode` is specified by `auto-mode-alist` or by file-local variables. Conversely,

```
  (add-to-list 'major-mode-remap-alist '(c-mode))
```

will force Emacs to never remap `c-mode` to any other mode.

The default value of `major-mode-remap-alist` is `nil`, so no remapping takes place.  However, loading some Lisp packages or features might introduce mode remapping, because Emacs assumes that loading those means the user prefers using an alternative mode.  So for predictable behavior, we recommend that you always customize `major-mode-remap-alist` to express your firm preferences, because this variable overrides any remapping that Emacs might decide to perform internally.



If you have changed the major mode of a buffer, you can return to the major mode Emacs would have chosen automatically, by typing M-x normal-mode.  This is the same function that `find-file` calls to choose the major mode.  If the buffer is visiting a file, this command also processes the file’s ‘-*-’ line and file-local variables list (if any).  See [Local Variables in Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Variables.html). If the buffer doesn’t visit a file, the command processes only the major mode specification, if any, in the ‘-*-’ line and in the file-local variables list.  M-x normal-mode takes the mode remapping into consideration, so if you customized `major-mode-remap-alist` after the buffer’s major mode was chosen by Emacs, `normal-mode` could turn on a mode that is different from the one Emacs chose originally.



The commands C-x C-w and `set-visited-file-name` change to a new major mode if the new file name implies a mode (see [Saving Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/Saving.html)). (C-x C-s does this too, if the buffer wasn’t visiting a file.) However, this does not happen if the buffer contents specify a major mode, and certain special major modes do not allow the mode to change.  You can turn off this mode-changing feature by setting `change-major-mode-with-file-name` to `nil`.



## 26 Indentation



*Indentation* refers to inserting or adjusting *whitespace characters* (space and/or tab characters) at the beginning of a line of text.  This chapter documents indentation commands and options which are common to Text mode and related modes, as well as programming language modes.  See [Indentation for Programs](https://www.gnu.org/software/emacs/manual/html_node/emacs/Program-Indent.html), for additional documentation about indenting in programming modes.



The simplest way to perform indentation is the TAB key.  In most major modes, this runs the command `indent-for-tab-command`. (In C and related modes, TAB runs the command `c-indent-line-or-region`, which behaves similarly, see [Commands for C Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/C-Indent.html)).

- TAB

  Insert whitespace, or indent the current line, in a mode-appropriate way (`indent-for-tab-command`).  If the region is active, indent all the lines within it.

The exact behavior of TAB depends on the major mode.  In Text mode and related major modes, TAB normally inserts some combination of space and tab characters to advance point to the next tab stop (see [Tab Stops](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Stops.html)).  For this purpose, the position of the first non-whitespace character on the preceding line is treated as an additional tab stop, so you can use TAB to align point with the preceding line.  If the region is active (see [Operating on the Region](https://www.gnu.org/software/emacs/manual/html_node/emacs/Using-Region.html)), TAB acts specially: it indents each line in the region so that its first non-whitespace character is aligned with the preceding line.

In programming modes, TAB indents the current line of code in a way that makes sense given the code in the preceding lines.  If the region is active, all the lines in the region are indented this way. If point was initially within the current line’s indentation, it is repositioned to the first non-whitespace character on the line.

If you just want to insert a tab character in the buffer, type C-q TAB (see [Inserting Text](https://www.gnu.org/software/emacs/manual/html_node/emacs/Inserting-Text.html)).



### 26.1 Indentation Commands

Apart from the TAB (`indent-for-tab-command`) command, Emacs provides a variety of commands to perform indentation in other ways.

- C-M-o[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html#index-C_002dM_002do)

  Split the current line at point (`split-line`).  The text on the line after point becomes a new line, indented to the same column where point is located.  This command first moves point forward over any spaces and tabs.  Afterward, point is positioned before the inserted newline.

- M-m

  Move (forward or back) to the first non-whitespace character on the current line (`back-to-indentation`).  If there are no non-whitespace characters on the line, move to the end of the line.

- M-i[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html#index-M_002di)

  Indent whitespace at point, up to the next tab stop (`tab-to-tab-stop`).  See [Tab Stops](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Stops.html).

- M-x indent-relative

  Insert whitespace at point, until point is aligned with the first non-whitespace character on the previous line (actually, the last non-blank line).  If point is already farther right than that, run `tab-to-tab-stop` instead—unless called with a numeric argument, in which case do nothing.

- M-^[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html#index-M_002d_005e)

  Merge the previous and the current line (`delete-indentation`). This joins the two lines cleanly, by replacing any indentation at the front of the current line, together with the line boundary, with a single space. As a special case (useful for Lisp code), the single space is omitted if the characters to be joined are consecutive opening and closing parentheses, or if the junction follows another newline. If there is a fill prefix, M-^ deletes the fill prefix if it appears after the newline that is deleted.  See [The Fill Prefix](https://www.gnu.org/software/emacs/manual/html_node/emacs/Fill-Prefix.html). With a prefix argument, join the current line to the following line. If the region is active, and no prefix argument is given, join all lines in the region instead.

- C-M-\[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html#index-C_002dM_002d_005c)

  Indent all the lines in the region, as though you had typed TAB at the beginning of each line (`indent-region`). If a numeric argument is supplied, indent every line in the region to that column number.

- C-x TAB[ ¶](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html#index-C_002dx-TAB)

  Indent all lines that begin in the region, moving the affected lines as a rigid unit (`indent-rigidly`). If called with no argument, this command activates a transient mode for adjusting the indentation of the affected lines interactively.  While this transient mode is active, typing LEFT or RIGHT indents leftward and rightward, respectively, by one space.  You can also type S-LEFT or S-RIGHT to indent leftward or rightward to the next tab stop (see [Tab Stops](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Stops.html)). Typing any other key disables the transient mode, and this key is then acted upon as normally. If called with a prefix argument n, this command indents the lines forward by n spaces (without enabling the transient mode). Negative values of n indent backward, so you can remove all indentation from the lines in the region using a large negative argument, like this:  `C-u -999 C-x TAB `



### 26.2 Tab Stops



Emacs defines certain column numbers to be *tab stops*.  These are used as stopping points by TAB when inserting whitespace in Text mode and related modes (see [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html)), and by commands like M-i (see [Indentation Commands](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation-Commands.html)).  The variable `tab-stop-list` controls these positions.  The default value is `nil`, which means a tab stop every 8 columns.  The value can also be a list of zero-based column numbers (in increasing order) at which to place tab stops.  Emacs extends the list forever by repeating the difference between the last and next-to-last elements.



Instead of customizing the variable `tab-stop-list` directly, a convenient way to view and set tab stops is via the command M-x edit-tab-stops.  This switches to a buffer containing a description of the tab stop settings, which looks like this:

```
        :       :       :       :       :       :
0         1         2         3         4
0123456789012345678901234567890123456789012345678
To install changes, type C-c C-c
```

The first line contains a colon at each tab stop.  The numbers on the next two lines are present just to indicate where the colons are. If the value of `tab-stop-list` is `nil`, as it is by default, no colons are displayed initially.

You can edit this buffer to specify different tab stops by placing colons on the desired columns.  The buffer uses Overwrite mode (see [Minor Modes](https://www.gnu.org/software/emacs/manual/html_node/emacs/Minor-Modes.html)).  Remember that Emacs will extend the list of tab stops forever by repeating the difference between the last two explicit stops that you place.  When you are done, type C-c C-c to make the new tab stops take effect.  Normally, the new tab stop settings apply to all buffers.  However, if you have made the `tab-stop-list` variable local to the buffer where you called M-x edit-tab-stops (see [Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Locals.html)), then the new tab stop settings apply only to that buffer.  To save the tab stop settings for future Emacs sessions, use the Customize interface to save the value of `tab-stop-list` (see [Easy Customization Interface](https://www.gnu.org/software/emacs/manual/html_node/emacs/Easy-Customization.html)).

Note that the tab stops discussed in this section have nothing to do with how tab characters are displayed in the buffer.  Tab characters are always displayed as empty spaces extending to the next *display tab stop*.  See [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html).



### 26.3 Tabs vs. Spaces

Normally, indentation commands insert (or remove) the shortest possible series of tab and space characters so as to align to the desired column.  Tab characters are displayed as a stretch of empty space extending to the next *display tab stop*.  By default, there is one display tab stop every `tab-width` columns (the default is 8).  See [How Text Is Displayed](https://www.gnu.org/software/emacs/manual/html_node/emacs/Text-Display.html).



If you prefer, all indentation can be made from spaces only.  To request this, set the buffer-local variable `indent-tabs-mode` to `nil`.  See [Local Variables](https://www.gnu.org/software/emacs/manual/html_node/emacs/Locals.html), for information about setting buffer-local variables.  Note, however, that C-q TAB always inserts a tab character, regardless of the value of `indent-tabs-mode`.

One reason to set `indent-tabs-mode` to `nil` is that not all editors display tab characters in the same way.  Emacs users, too, may have different customized values of `tab-width`.  By using spaces only, you can make sure that your file always looks the same. If you only care about how it looks within Emacs, another way to tackle this problem is to set the `tab-width` variable in a file-local variable (see [Local Variables in Files](https://www.gnu.org/software/emacs/manual/html_node/emacs/File-Variables.html)).



There are also commands to convert tabs to spaces or vice versa, always preserving the columns of all non-whitespace text.  M-x tabify scans the region for sequences of spaces, and converts sequences of at least two spaces to tabs if that can be done without changing indentation.  M-x untabify changes all tabs in the region to appropriate numbers of spaces.



### 26.4 Convenience Features for Indentation



The variable `tab-always-indent` tweaks the behavior of the TAB (`indent-for-tab-command`) command.  The default value, `t`, gives the behavior described in [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html).  If you change the value to the symbol `complete`, then TAB first tries to indent the current line, and if the line was already indented, it tries to complete the text at point (see [Completion for Symbol Names](https://www.gnu.org/software/emacs/manual/html_node/emacs/Symbol-Completion.html)).  If the value is `nil`, then TAB indents the current line only if point is at the left margin or in the line’s indentation; otherwise, it inserts a tab character.



If `tab-always-indent` is `complete`, whether to expand or indent can be further customized via the `tab-first-completion` variable.  For instance, if that variable is `eol`, only complete if point is at the end of a line.  See [Mode-Specific Indent](https://www.gnu.org/software/emacs/manual/html_node/elisp/Mode_002dSpecific-Indent.html#Mode_002dSpecific-Indent) in The Emacs Lisp Reference Manual, for further details.



Electric Indent mode is a global minor mode that automatically indents the line after every RET you type.  This mode is enabled by default.  To toggle this minor mode, type M-x electric-indent-mode.  To toggle the mode in a single buffer, use M-x electric-indent-local-mode.



### 26.5 Code Alignment



*Alignment* is the process of adjusting whitespace in a sequence of lines in the region such that in all lines certain parts begin at the same column.  This is usually something you do to enhance readability of a piece of text or code.  The classic example is aligning a series of assignments in C-like programming languages:

```
int a = 1;
short foo = 2;
double blah = 4;
```

is commonly aligned to:

```
int    a    = 1;
short  foo  = 2;
double blah = 4;
```



You can use the command M-x align to align lines in the current region.  This command knows about common alignment patterns across many markup and programming languages.  It encodes these patterns as a set of *alignment rules*, that say how to align different kinds of text in different contexts.



The user option `align-rules-list` says which alignment rules M-x align should consult.  The value of this option is a list with elements describing alignment rules.  Each element is a cons cell `(title . attributes)`, where title is the name of the alignment rule as a symbol, and attributes is a list of rule attributes that define when the rule should apply and how it partitions and aligns lines.  Each rule attribute is a cons cell `(attribute . value)`, where attribute is the name of attribute and value is its value.  The only required attribute is `regexp`, whose value is a regular expression with sub-expressions matching the parts of each line where M-x align should expand or contract whitespace (see [Backslash in Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Backslash.html)).  See the documentation string of `align-rules-list` (C-h v align-rules-list RET) for a full description of possible alignment rule attributes.  By default, this option is set to a long list of alignment rules for many languages that Emacs supports.  The default rules use the `modes` rule attribute to specify major modes in which M-x align should apply them.  Major modes can also override `align-rules-list` by setting the buffer-local variable `align-mode-rules-list` to a non-`nil` list of alignment rules.  When `align-mode-rules-list` is non-`nil`, M-x align consults it instead of `align-rules-list`.



Besides alignment rules, M-x align uses another kind of rules called *exclusion rules*.  The exclusion rules say which parts in the region M-x align should not align and instead leave them intact.  The user option `align-exclude-rules-list` specifies these exclusion rules.  Similarly to `align-rules-list`, the value of `align-exclude-rules-list` is also a list of cons cells that describe the exclusion rules.  By default, `align-exclude-rules-list` includes rules that exclude alignment in quoted strings and comments in Lisp, C and other languages.  Beyond the default exclusion rules in `align-exclude-rules-list`, major modes can define bespoke exclusion rules by setting `align-mode-exclude-rules-list` to a non-`nil` list of rules, this overrides `align-exclude-rules-list` just like `align-mode-rules-list` overrides `align-rules-list`.



M-x align splits the region into a series of *sections*, usually sequences of non-blank lines, and aligns each section according to all matching alignment rule by expanding or contracting stretches of whitespace.  M-x align consistently aligns all lines inside a single section, but it may align different sections in the region differently.  The user option `align-region-separate` specifies how M-x align separates the region to sections.  This option can be one of the symbols `entire`, `group`, or a regular expression.  If `align-region-separate` is `entire`, Emacs aligns the entire region as a single section.  If this option is `group`, Emacs aligns each group of consecutive non-blank lines in the region as a separate section.  If `align-region-separate` is a regular expression, M-x align scans the region for matches to that regular expression and treats them as section separators.  By default `align-region-separate` is set to a regular expression that matches blank lines and lines that contains only whitespace and a single curly brace (‘{’ or ‘}’).  For special cases where regular expressions are not accurate enough, you can also set `align-region-separate` to a function that says how to separate the region to alignment sections.  See the documentation string of `align-region-separate` for more details.  Specific alignment rules can override the value of `align-region-separate` and define their own section separator by specifying the `separate` rule attribute.

If you call M-x align with a prefix argument (C-u), it enables more alignment rules that are often useful but may sometimes be too intrusive.  For example, in a Lisp buffer with the following form:

```
(set-face-attribute 'mode-line-inactive nil
                    :box nil
                    :background nil
                    :underline "black")
```

Typing (C-u M-x align) yields:

```
(set-face-attribute 'mode-line-inactive nil
                    :box                nil
                    :background         nil
                    :underline          "black")
```

In most cases, you should try M-x align without a prefix argument first, and if that doesn’t produce the right result you can undo with C-/ and try again with C-u M-x align.



You can use the command M-x align-highlight-rule to visualize the effect of a specific alignment or exclusion rule in the current region.  This command prompts you for the title of a rule and highlights the parts on the region that this rule affects.  For alignment rules, this command highlights the whitespace that M-x align would expand or contract, and for exclusion this command highlights the parts that M-x align would exclude from alignment.  To remove the highlighting that this command creates, type M-x align-unhighlight-rule.



The command M-x align-current is similar to M-x align, except that it operates only on the alignment section that contains point regardless of the current region.  This command determines the boundaries of the current section according to the section separators that `align-region-separate` define.  M-x align-entire is another variant of M-x align, that disregards `align-region-separate` and aligns the entire region as a single alignment section with consistent alignment.  If you set `align-region-separate` to `entire`, M-x align behaves like M-x align-entire by default.  To illustrate the effect of aligning the entire region as a single alignment section, consider the following code:

```
one = 1;
foobarbaz = 2;

spam = 3;
emacs = 4;
```

when the region covers all of these lines, typing M-x align yields:

```
one       = 1;
foobarbaz = 2;

spam  = 3;
emacs = 4;
```

On the other hand, M-x align-entire aligns all of the lines as a single section, so the ‘=’ appears at the same column in all lines:

```
one       = 1;
foobarbaz = 2;

spam      = 3;
emacs     = 4;
```



The command M-x align-regexp lets you align the current region with an alignment rule that you define ad-hoc, instead of using the predefined rules in `align-rules-list`.  M-x align-regexp prompts you for a regular expression and uses that expression as the `regexp` attribute for an ad-hoc alignment rule that this command uses to align the current region.  By default, this command adjusts the whitespace that matches the first sub-expression of the regular expression you specify.  If you call M-x align-regexp with a prefix argument, it also prompts you for the sub-expression to use and lets you specify the amount of whitespace to use as padding, as well as whether to apply the rule repeatedly to all matches of the regular expression in each line.  See [Backslash in Regular Expressions](https://www.gnu.org/software/emacs/manual/html_node/emacs/Regexp-Backslash.html), for more information about regular expressions and their sub-expressions.



If the user option `align-indent-before-aligning` is non-`nil`, Emacs indents the region before aligning it with M-x align.  See [Indentation](https://www.gnu.org/software/emacs/manual/html_node/emacs/Indentation.html).  By default `align-indent-before-aligning` is set to `nil`.



The user option `align-to-tab-stop` says whether aligned parts should start at a tab stop (see [Tab Stops](https://www.gnu.org/software/emacs/manual/html_node/emacs/Tab-Stops.html)).  If this option is `nil`, M-x align uses just enough whitespace for alignment, disregarding tab stops.  If this is a non-`nil` symbol, M-x align checks the value of that symbol, and if this value is non-`nil`, M-x align aligns to tab stops.  By default, this option is set to `indent-tabs-mode`, so alignment respects tab stops in buffers that use tabs for indentation.  See [Tabs vs. Spaces](https://www.gnu.org/software/emacs/manual/html_node/emacs/Just-Spaces.html).



The user option `align-default-spacing` specifies the default amount of whitespace that M-x align and its related commands use for padding between the different parts of each line when aligning it. When `align-to-tab-stop` is `nil`, the value of `align-default-spacing` is the number of spaces to use for padding; when `align-to-tab-stop` is non-`nil`, the value of `align-default-spacing` is instead the number of tab stops to use.  Each alignment rule can override the default that `align-default-spacing` specifies with the `spacing` attribute rule.



## 27 Commands for Human Languages



## 28 Editing Programs



## 29 Compiling and Testing Programs
