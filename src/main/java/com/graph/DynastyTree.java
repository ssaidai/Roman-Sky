package com.graph;

import com.data.Person;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraphSelectionModel;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
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
            return null;
        }

        public Person getSource(){
            return (Person) super.getSource();
        }

        public Person getTarget(){
            return (Person) super.getTarget();
        }
    }

    private final Graph<Person, RelationshipEdge> graph = new SimpleGraph<>(RelationshipEdge.class);
    private static final String MARRIED = "married";
    private static final String KIN = "kin";
    private static final String ADOPTED = "adopted";

    private final JGraphXAdapter<Person, RelationshipEdge> graphAdapter;

    private Object[] marriedCells, adoptedCells, kinCells;

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
            /*List<Person> parents = entityList.stream().filter(p -> person.getParentsHrefs().contains(p.getHref())).collect(Collectors.toList());
            for (Person parent: parents)
                graph.addEdge(parent, person, new RelationshipEdge(KIN));*/
        }

        //  TODO:   https://jgraph.github.io/mxgraph/docs/manual_javavis.html   ---   mxGraph documentation
        //  FIXME:  place wives at same level of the husbands

        this.graphAdapter = new JGraphXAdapter<>(graph);
        new mxHierarchicalLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        //new mxParallelEdgeLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        HashMap<RelationshipEdge, mxICell> edgemxICellHashMap = graphAdapter.getEdgeToCellMap();
        ArrayList<Object> marriedList = new ArrayList<>();
        ArrayList<Object> adoptedList = new ArrayList<>();
        ArrayList<Object> kinList = new ArrayList<>();
        graphAdapter.setConnectableEdges(false);
        graphAdapter.setCellsResizable(false);
        graphAdapter.setCellsMovable(false);
        mxConstants.DEFAULT_MARKERSIZE = 0;
        graphAdapter.getSelectionModel().addListener(mxEvent.CHANGE, new mxEventSource.mxIEventListener() {
            @Override
            public void invoke(Object o, mxEventObject mxEventObject) {
                mxGraphSelectionModel sm = (mxGraphSelectionModel) o;
                mxCell cell = (mxCell) sm.getCell();
                if (cell != null && cell.isVertex()) {
                    Person temp = (Person)cell.getValue();
                    try {
                        Desktop.getDesktop().browse(new URI(temp.getHref()));
                    } catch (IOException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        for(RelationshipEdge edge : edgemxICellHashMap.keySet()){
            if(edge.getLabel().equals("married"))
                marriedList.add(edgemxICellHashMap.get(edge));
            if(edge.getLabel().equals("adopted"))
                adoptedList.add(edgemxICellHashMap.get(edge));
            else
                kinList.add(edgemxICellHashMap.get(edge));
        }

        this.marriedCells = new Object[marriedList.size()];
        marriedList.toArray(this.marriedCells);

        setCellsStyle("married", "78130C");
        //graphAdapter.setCellStyle("strokeColor=#78130C", marriedCells);

        this.adoptedCells = new Object[adoptedList.size()];
        adoptedList.toArray(this.adoptedCells);

        setCellsStyle("adopted", "422AAD");
        graphAdapter.setCellStyle("dashed=true", adoptedCells);

        this.kinCells = new Object[kinList.size()];
        kinList.toArray(this.kinCells);
    }

    public JGraphXAdapter<Person, RelationshipEdge> getGraphAdapter(){
        return this.graphAdapter;
    }

    public void setCellsStyle(String cellType, String colorInHex){
        String color = "strokeColor=#" + colorInHex;
        switch (cellType){
            case "married":
                this.graphAdapter.setCellStyle(color, marriedCells);
                break;
            case "kin":
                this.graphAdapter.setCellStyle(color, kinCells);
                break;
            case "adopted":
                this.graphAdapter.setCellStyle(color, adoptedCells);
                break;
        }
    }

}


