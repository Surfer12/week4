graph TD
    A[Computer System Overview] --> B[Hardware Components]
    A --> C[Software Components]

    subgraph Hardware
        B --> D[Processing Unit]
        B --> E[Memory Systems]
        B --> F[Input/Output]

        D --> G[CPU]
        D --> H[ALU]

        E --> I[Primary Memory]
        E --> J[Secondary Storage]

        F --> K[Input Devices]
        F --> L[Output Devices]
    end

    subgraph Software
        C --> M[System Software]
        C --> N[Application Software]

        M --> O[Operating System]
        M --> P[Device Drivers]

        N --> Q[User Programs]
        N --> R[Utilities]
    end

    style A fill:#f9f,stroke:#333,stroke-width:2px
    style B fill:#bbf,stroke:#333
    style C fill:#bbf,stroke:#333
