package wtf.kinggen.api;

/**
 * Records of all the different API-Endpoints.
 */
public enum Endpoint {

    // Alt Endpoint to generate an Alt.
    GENERATE("/alt","key=%s"),

    // Profile Endpoint to get information specific to the owner of the api-key.
    PROFILE("/profile","key=%s");

    // Base API information such as the currently used Version and the URL.
    private static final int BASE_API_VERSION = 2;
    public static final String BASE_API_URL = String.format("https://kinggen.wtf/api/v%d", BASE_API_VERSION);

    // The Endpoint routes.
    private final String route;

    // The Endpoint route, but formatted.
    private final String formattableRoute;

    // Does the Endpoint have any Queries?
    private final boolean hasQueryParams;

    // The Query parameters.
    private final String queryParams;

    /**
     * Create an Endpoint on the API.
     * @param route the route.
     * @param queryParams the Queries.
     */
    Endpoint(String route, String... queryParams)
    {
        this.route = route;
        this.hasQueryParams = queryParams.length > 0;

        if(hasQueryParams)
        {
            StringBuilder b = new StringBuilder();

            for(int i = 0; i < queryParams.length; i++)
            {
                b.append(i == 0? '?' : '&');
                b.append(queryParams[i]);
            }

            this.formattableRoute = route + b;
            this.queryParams = b.toString();
        }
        else
        {
            this.formattableRoute = route;
            this.queryParams = "";
        }
    }

    /**
     * Get the route.
     * @return the route.
     */
    public String getRoute()
    {
        return route;
    }

    /**
     * Check if the Endpoint requests requires any Queries.
     * @return true, if it requires queries | false, if not.
     */
    public boolean hasQueryParams()
    {
        return hasQueryParams;
    }

    /**
     * Compile the queries into a URL friendly format.
     * @param values the Query Values.
     * @return the formatted String.
     */
    public String compileQueryParams(Object... values) {
        return String.format(queryParams, values).replaceFirst("\\?", "");
    }

    /**
     * Get base API url with the route of the Endpoint.
     * @return a combined Version of both.
     */
    public String getRouteWithBaseUrl()
    {
        return BASE_API_URL + route;
    }

    /**
     * Compile the Endpoint into a valid url.
     * @param values the query values.
     * @return a valid URL String combination.
     */
    public String compile(Object... values)
    {
        if (hasQueryParams) {
            return BASE_API_URL + String.format(formattableRoute, values);
        } else {
            return BASE_API_URL + formattableRoute;
        }
    }

}
