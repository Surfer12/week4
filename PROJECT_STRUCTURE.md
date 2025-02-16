# Project Directory Reorganization for LTspice

This project has been reorganized for better manageability and clarity. Below is an overview of the new directory structure, naming conventions, and usage guidelines.

## Directory Structure

- **ltspice/**: Contains all LTspice circuit files, schematics, and simulation files.
  - **basic_gates/**: Basic logic gate circuits (formerly in logic-library).
  - **arithmetic/**:
    - **half_adder/**: Half adder circuit files.
    - **full_adder/**: Full adder circuit files.
  - **integration/**:
    - **cpu_integration/**: CPU-related LTspice integration circuits.
    - **integration/**: Other integration circuits.
  - **microarchitecture/**:
    - **microarch/**: Microarchitecture components.
    - **fsm/**: Finite State Machine implementations.
  - **components/**: Miscellaneous LTspice components.
  - **simulation/**:
    - **simulation_files/**: Simulation configuration and circuitry files.
    - **verification/**: Verification circuit files.
  - **schematics/**: Schematics for LTspice circuits.

- **docs/**: Documentation for the project.
  - **documentation/**: Detailed documentation files (formerly '@Documentation').
  - **todos/**: TODOs and related documents (from TODODocs).
  - Legacy README: `legacy_README.md` contains the original project documentation.
  - Other files: `v18.md`, `v18`, and `types-of-geese.md`.

- **workspaces/**: Workspace configuration files.
  - `project.code-workspace`
  - `week4micro.code-workspace`

- **experiments/**: Experimental designs, test benches, and lab circuits.
  - Contains files moved from `labs` and `graphical-test-revisions`.

- **assets/**: Project assets including images, references, and resources.
  - Contains content moved from `Images`, `References`, and `resources` (as `assets/resources`).

- **code/**: Source code for project tools.
  - Contains `src` and `lib` directories.

- **build/**: Build scripts and output directories (renamed from `Build`).

- **tools/**: Tools and utility scripts.
  - **conversions/**: Conversion utilities.
  - **scripts/**: Utility scripts.

## Naming Conventions and Guidelines

- LTspice circuits are organized by functionality (gates, arithmetic, integration, etc.).
- Documentation is maintained in the `docs/` directory with legacy content archived in `legacy_README.md`.
- Workspace and source code files are separated to ensure modularity and clarity.
- Assets are grouped by type for easier retrieval.
- Experimental and verification files are kept distinct from production-level designs.

## Usage Guidelines

1. Place new LTspice circuits under the appropriate subdirectory within `ltspice/`.
2. Update documentation in `docs/documentation/` as necessary.
3. Manage IDE configurations within the `workspaces/` folder.
4. Coordinate experimental contributions in the `experiments/` directory.
5. Place source code and utility scripts in `code/` and `tools/` respectively.

This reorganization streamlines project management and simplifies navigation across project components. 