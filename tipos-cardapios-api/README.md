Project Name: Restaurant Management System

Description:
This Spring Boot project is designed to serve as a backend for a restaurant management system. It provides endpoints to manage various types of menus in the restaurant.

Endpoints:

    Save Type Menu
        URL: POST /tipos-cardapios
        Description: Saves a new type of menu.
        Request Body: TypeMenu object
        Response: Returns the saved TypeMenu object or a URI for accessing the newly created resource.

    Update Status
        URL: PATCH /tipos-cardapios/{id}/status/{status}
        Description: Updates the status of a type of menu (active or inactive).
        Parameters:
            id: The ID of the type menu to update.
            status: New status value ("active" or "inactive").
        Response: Returns the updated TypeMenu object.

    List Type Menus
        URL: GET /tipos-cardapios
        Description: Retrieves a list of type menus. Optionally filters by name.
        Query Parameters:
            nome (optional): Name of the type menu to filter.
            pagina (optional, default: 0): Page number for pagination.
            size (optional, default: 20): Number of items per page.
        Response: Returns a paginated list of TypeMenu objects.

    Find Type Menu by ID
        URL: GET /tipos-cardapios/id/{id}
        Description: Retrieves a type menu by its ID.
        Parameters: id: The ID of the type menu.
        Response: Returns the TypeMenu object with the specified ID.

    Update Type Menu
        URL: PUT /tipos-cardapios/{id}
        Description: Updates an existing type menu.
        Parameters: id: The ID of the type menu to update.
        Request Body: TypeMenu object with updated information.
        Response: Returns the updated TypeMenu object.

Database Configuration:

    This project uses PostgreSQL as the database.
    Docker is utilized for setting up the development environment, including the database.
    Docker volumes are employed for managing database data persistence.

Additional Information:

    The HTTP requests are organized within the http folder, using Bruno Ide.