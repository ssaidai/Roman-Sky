package com.graph;

import com.data.Person;
import com.mxgraph.layout.*;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class DynastyTree {
    private class RelationshipEdge extends DefaultEdge{
        private final String label;

        public RelationshipEdge(String label)
        {
            this.label = label;
        }

        public String getLabel()
        {
            return label;
        }

        @Override
        public String toString()
        {
            return label;
        }

        public Person getSource(){
            return (Person) super.getSource();
        }

        public Person getTarget(){
            return (Person) super.getTarget();
        }
    }

    private final Graph<Person, RelationshipEdge> graph = new Multigraph<>(RelationshipEdge.class);
    private static final String MARRIED = "married";
    private static final String KIN = "kin";
    private static final String ADOPTED = "adopted";

    public DynastyTree(Set<Person> entityList){
        //  TODO:   order entities by bornDate before adding it to the graph (set adds them in random order)
        Graphs.addAllVertices(this.graph, entityList);

        for(Person person : graph.vertexSet()){
            List<Person> relatives = entityList.stream().filter(p -> person.getMarriedHrefs().contains(p.getHref())).collect(Collectors.toList());
            for(Person relative : relatives){
                graph.addEdge(person, relative, new RelationshipEdge(MARRIED));
            }
            List<Person> children = entityList.stream().filter(p -> person.getChildren().containsKey(p.getHref())).collect(Collectors.toList());
            for(Person child : children){
                if(person.getChildren().get(child.getHref()))
                    graph.addEdge(person, child, new RelationshipEdge(ADOPTED));
                else
                    graph.addEdge(person, child, new RelationshipEdge(KIN));
            }
        }

        //  TODO:   https://jgraph.github.io/mxgraph/docs/manual_javavis.html   ---   mxGraph documentation
        //  FIXME:  change edge color by relation type and place wives at same level of the husband
    }

    public JGraphXAdapter<Person, RelationshipEdge> getGraphAdapter(){
        JGraphXAdapter<Person, RelationshipEdge> graphAdapter = new JGraphXAdapter<>(graph);

        mxIGraphLayout layout = new mxHierarchicalLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());


        HashMap<RelationshipEdge, mxICell> edgemxICellHashMap = graphAdapter.getEdgeToCellMap();
        ArrayList<Object> cellsList = new ArrayList<>();

        for(RelationshipEdge edge : edgemxICellHashMap.keySet()){
            if(edge.getLabel().equals("married")){
                cellsList.add(edgemxICellHashMap.get(edge));
            }
        }
        Object[] cells = new Object[cellsList.size()];
        cellsList.toArray(cells);

        graphAdapter.setCellStyle("strokeColor=#78130C", cells);
        return graphAdapter;
    }

}


