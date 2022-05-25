package com.graph;

import com.data.Person;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class DynastyTree {
    private class RelationshipEdge extends DefaultEdge{
        private String label;

        /**
         * Constructs a relationship edge
         *
         * @param label the label of the new edge.
         *
         */
        public RelationshipEdge(String label)
        {
            this.label = label;
        }

        /**
         * Gets the label associated with this edge.
         *
         * @return edge label
         */
        public String getLabel()
        {
            return label;
        }

        @Override
        public String toString()
        {
            return "(" + getSource() + " : " + getTarget() + " : " + label + ")";
        }
    }

    private Graph<Person, RelationshipEdge> graph = new Multigraph<>(RelationshipEdge.class);
    private static final String MARRIED = "married";
    private static final String KIN = "kin";
    private static final String ADOPTED = "adopted";

    public DynastyTree(Set<Person> entityList){
        Graphs.addAllVertices(this.graph, entityList);

        for(Person person : graph.vertexSet()){
            for(Person relative : person.getMarried()){
                graph.addEdge(person, relative, new RelationshipEdge(MARRIED));
            }
            for(Person child : person.getChildren().keySet()){
                if(person.getChildren().get(child))
                    graph.addEdge(person, child, new RelationshipEdge(ADOPTED));
                else
                    graph.addEdge(person, child, new RelationshipEdge(KIN));
            }
        }

        JFrame frame = new JFrame("Grafo de noemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JGraphXAdapter<Person, RelationshipEdge> graphAdapter =
                new JGraphXAdapter<Person, RelationshipEdge>(graph);

        mxIGraphLayout layout = new mxEdgeLabelLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

}


