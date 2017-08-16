# Ice Builder for Eclipse

The Ice Builder for Eclipse provides a plug-in which automates the compilation
of [Slice](https://doc.zeroc.com/display/Ice/The+Slice+Language) files using the
[Slice-to-Java](https://doc.zeroc.com/display/Ice/slice2java+Command-Line+Options)
compiler and manages the resulting generated code.

An [Ice](https://github.com/zeroc-ice/ice) installation with `slice2java`
version 3.4.2 or higher is required.

The Ice Builder for Eclipse plug-in provides the following features:
 - Automatically compiles all the Slice files in your project
 - Incrementally recompiles Slice files after modifications
 - Maintains dependencies between Slice files
 - Highlights Slice compilation errors in your source code
 - Manages code generated by `slice2java` and removes obsolete generated files
automatically

## Contents

 - [Installing the Ice Builder Plug-in](#installing-the-ice-builder-plug-in)
 - [Usage](#Usage)
   - [Configuring the Plug-in](#configuring-the-plug-in)
   - [Activating the Plug-in for a Project](#activating-the-plug-in-for-a-project)
   - [Configuring Project Settings](#configuring-project-settings)
   - [Using the Plug-in](#using-the-plugin)
 - [Building the Plug-in fromSource](#building-the-plugin-from-source)

## Installing the Ice Builder Plug-in

The Ice Builder for Eclipse is distributed through the Eclipse Marketplace:

 1. From the `Help` menu, choose `Eclipse Marketplace`
 2. Search for `Ice Builder`
 3. Click `Install` to install the `Ice Builder` plug-in

## Upgrade Notes

### From Ice Builder 4.0

The Ice Builder 4.0 adds automatically all the project's Slice directories
to the list of include directories during compilation.

Newer versions of Ice Builder no longer adds these directories to the list
of include directories. As a result, when upgrading from Ice Builder 4.0 to
the latest Ice Builder, you may need to add some of your project's Slice
directories to the Include Directories (see
[Configuring Project Settings](#configuring-project-settings) below).

### From a 3.x Plug-in

If you are upgrading from an Ice plug-in version 3.x, you should uninstall
the old plug-in before installing the Ice Builder. For such versions, after
installation it might be necessary to manually reconfigure the Ice Builder
settings as old settings are not automatically migrated.

## Usage

### Configuring the Plug-in

The plug-in preferences can be accessed by navigating to `Window -> Preferences`
and selecting `Ice Builder`.
![Preferences Dialog](/Screenshots/preferences.png)

You can use `Ice Home` to specify where `slice2java` and the associated Slice
files are located. Ice Builder looks for `slice2java` in the `bin` subdirectory
of `Ice Home`, and for the Slice files in the following subdirectories of `Ice Home`:
 - `share/slice`
 - `share/ice/slice`
 - `share/Ice-<version>/slice`, where <version> is the version returned by
    `slice2java -v`
 - `slice`

If you leave `Ice Home` unset, the Ice Builder uses the following
platform-depedent `Ice Home`:

| Platform | Default Ice Home                                         |
| -------- | -------------------------------------------------------- |
| Linux    | /usr                                                     |
| macOS    | /usr/local                                               |
| Windows  | Latest Ice installation recorded in the Windows registry |

Leaving `Ice Home` unset does not record the `Ice Home` path in your project
file, which can be advantageous.

`Build Automatically` allows you to choose whether Slice files should be rebuilt
automatically as the are updated; it is generally recommended to disable this
option for larger projects.

### Activating the Plug-in for a Project

The plug-in is enabled on a per project basis. To enable it, right click on the
project, choose `Ice Builder` and select `Add Ice Builder`.

Upon activation, the project immediately creates a `generated` directory to hold
the Java source files the Slice compiler generates from your Slice files.
![Activating the Plug-in](/Screenshots/activation.png)
To de-activate the plugin, simply navigate to the `Ice Builder` menu and instead
select `Remove Ice Builder`. Note that removing the Ice Builder plug-in will not
affect your Slice files, but will remove all generated code.

### Configuring Project Settings

To configure the project-specific settings, select `Properties` from the
`Project` menu or right-click on the name of your project and choose
`Properties`.  Click on the `Ice Builder` menu to view the plug-in's settings
for the selected project.
![Project Properties Dialog](/Screenshots/properties.png)
The `Generated Code Directory` allows you to specify where all generated code
should be placed in the project. By default, the plug-in uses the `generated`
directory for this. To change the directory, you must first create the new
directory, and then click `Browse` to navigate to it. The new directory must be
empty, and the plug-in requires exclusive use of it; any files or changes made
to the directory will be lost during the next Slice compilation.

The `Include Directories` list allows you to specify Slice include directories
passed to `slice2java` through the `-I` option. These directories are searched
by `slice2java` in the order you specified. The Ice Builder adds automatically
the Slice directory found within `Ice Home` at the end of this list.

You can pass additional options to the Slice compiler with the `Additional
Options` filed. For a list of all supported options and their descriptions,
refer to `slice2java` page of the
[Ice Manual](https://doc.zeroc.com/display/Ice/slice2java+Command-Line+Options).

### Compiling Slice Files

Slice files can be manually compiled through Eclipse or by using the `Compile`
Button under the `Ice Builder` project menu.

If automatic building is enabled, Slice files will be compiled if either of
the following are true:
 * Any Slice files in the project have been updated since the last compilation
through the plug-in (this includes removing Slice files from projects).
 * The options used to compile Slice files have changed.

During a full build, the Ice Builder plug-in automatically searches all
directories within your project's scope for Slice files, and compiles all of
them, in addition to removing any obsolete generated code.

For incremental builds, only the Slice files that have changed in the project,
and the Slice files in the project that directly or indirectly reference them,
are recompiled, and their corresponding generated code updated.

## Building the Plug-in from Source

In order to build the plug-in, you need an installation of Eclipse suitable
for plug-in development.

You also need to install the Eclipse Plug-in Development Environment if it is
not already installed. Go to `Help > Eclipse Marketplace` and search for
`Eclipse PDE`, then click `Install` to install the Eclipse PDE.

To import the project choose `File > Import > General > Existing Projects into
Workspace` and select the java directory in this repository as the root
directory.

To create the plug-in, use `File > Export > Plug-in Development > Deployable
plug-ins and fragments`.
