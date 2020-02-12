from __future__ import print_function
from ortools.constraint_solver import pywrapcp
from ortools.constraint_solver import pywrapcp
from flask import Flask, jsonify
from flask import request
import json

app = Flask(__name__)

def create_data_model(initial_routes, distance_matrix):
    data = {}
    data['distance_matrix'] = distance_matrix
    data['initial_routes'] = initial_routes
    data['num_vehicles'] = 1
    data['depot'] = 0
    return data


def print_solution(data, manager, routing, solution):
    """Prints solution on console."""
    max_route_distance = 0
    return_output = ''
    for vehicle_id in range(data['num_vehicles']):
        index = routing.Start(vehicle_id)
        plan_output = '{"route":['
        route_distance = 0
        while not routing.IsEnd(index):
            plan_output += ' {}, '.format(manager.IndexToNode(index))
            previous_index = index
            index = solution.Value(routing.NextVar(index))
            route_distance += routing.GetArcCostForVehicle(
                previous_index, index, vehicle_id)
        plan_output += '{}\n'.format(manager.IndexToNode(index))
        plan_output += '], "distance": {}\n'.format(route_distance)
        plan_output += '}'
        print(plan_output)
        return_output += plan_output
        max_route_distance = max(route_distance, max_route_distance)
    print('Maximum of the route distances: {}m'.format(max_route_distance))
    return return_output




def calculateRoute(initial_routes, distance_matrix):
    """Solve the CVRP problem."""
    # Instantiate the data problem.
    data = create_data_model(initial_routes, distance_matrix)
    print(data)

    # Create the routing index manager.
    manager = pywrapcp.RoutingIndexManager(len(data['distance_matrix']),
                                           data['num_vehicles'], data['depot'])

    # Create Routing Model.
    routing = pywrapcp.RoutingModel(manager)


    # Create and register a transit callback.
    def distance_callback(from_index, to_index):
        """Returns the distance between the two nodes."""
        # Convert from routing variable Index to distance matrix NodeIndex.
        from_node = manager.IndexToNode(from_index)
        to_node = manager.IndexToNode(to_index)
        return data['distance_matrix'][from_node][to_node]

    transit_callback_index = routing.RegisterTransitCallback(distance_callback)

    # Define cost of each arc.
    routing.SetArcCostEvaluatorOfAllVehicles(transit_callback_index)

    # Add Distance constraint.
    dimension_name = 'Distance'
    routing.AddDimension(
        transit_callback_index,
        0,  # no slack
        3000,  # vehicle maximum travel distance
        True,  # start cumul to zero
        dimension_name)
    distance_dimension = routing.GetDimensionOrDie(dimension_name)
    distance_dimension.SetGlobalSpanCostCoefficient(100)

    initial_solution = routing.ReadAssignmentFromRoutes(data['initial_routes'], True)
    # print('Initial solution:')
    # print_solution(data, manager, routing, initial_solution)

    # Set default search parameters.
    search_parameters = pywrapcp.DefaultRoutingSearchParameters()

    # Solve the problem.
    solution = routing.SolveFromAssignmentWithParameters(
        initial_solution, search_parameters)

    # Print solution on console.
    if solution:
        print('Solution after search:')
        print_solution(data, manager, routing, solution)
        return print_solution(data, manager, routing, solution)


@app.route("/calculateRoute")
def hello():
    return calculateRoute([], [[0,1],[1,0]])

@app.route('/calculateRoute', methods=['POST'])
def foo():
    distance_matrix = request.json
    return calculateRoute([[]], distance_matrix)
    # return jsonify(initial_routes)

if __name__ == '__main__':
    app.run(debug=True)
