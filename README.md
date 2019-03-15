# Macro Simulate

Macro to simulate a keybord input of some code.

What it does is:

 - Enter a code (with numbers and letters).
 - Press `arrow down` once.
 - Repeat.

Configuration:
 - Timer Count Down = Tells the time until the macro starts.
 - Number or Repetitions = How many times the code will be pressed.

 - Interval to Press Down = How long one code will be pressed after
another.

![Macro Simulate Image](images/MacroSimulate.png)

# Register Simulate

Macro to simulate a register input into a system. 

What it does is:

 - Press `Enter` twice.
 - Enter a code like `AA000A`.
 - Press `Enter` twice.
 - Repeat.

This code `AA000A` is the location where the product with the serial
number is stored. It has the following meaning:

 - Two Letter to tells which street it is located.
 - Three Numbers to tell which column it is located.
 - A Letter to tells which row (level) it is located.
 
Configuration:
 - Level to create code
 - Letters of street
 - From-to columns
 - Is side? If columns are only odd or even

![Register Image](images/RegisterMacro.png)

# Location Simulate

Macro to input the location for products. A file is needed to get the
product codes.

This macro is specific for one system.

What it does is:

 - Enter a serial number.
 - Press `Enter` three times.
 - Press `Tab` thirty five times.
 - Press a code like `AA000A`.
 - Press `Tab` ten times.
 - Press `Enter`
 - Wait some time.
 - Press `p`
 - Press `Esc` twice.
 - Repeat.

This Macro needs a file to be loaded, where all the serial numbers are
stored.

This code `AA000A` is the location where the product with the serial
number is stored. It has the following meaning:

 - Two Letter to tells which street it is located.
 - Three Numbers to tell which column it is located.
 - A Letter to tells which row (level) it is located.
 
Configuration:
 - Level to create code
 - Letters of street
 - From-to columns
 - Is side? If columns are only odd or even

![Lacation Simulate Image](images/LocationMacro.png)
