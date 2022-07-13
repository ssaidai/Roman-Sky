package com.graph;

import com.data.Person;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
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

    private final Object[] marriedCells, adoptedCells, kinCells, vipCells, allCells;

    public DynastyTree(Set<Person> entityList){
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
            List<Person> parents = entityList.stream().filter(p -> person.getParents().containsKey(p.getHref())).collect(Collectors.toList());
            for (Person parent: parents)
                if(person.getParents().get(parent.getHref()))
                    graph.addEdge(parent, person, new RelationshipEdge(ADOPTED));
                else
                    graph.addEdge(parent, person, new RelationshipEdge(KIN));
        }

        this.graphAdapter = new JGraphXAdapter<>(graph);
        new mxHierarchicalLayout(graphAdapter).execute(graphAdapter.getDefaultParent());
        HashMap<RelationshipEdge, mxICell> edgemxICellHashMap = graphAdapter.getEdgeToCellMap();
        HashMap<Person, mxICell> vertexToCellMap = graphAdapter.getVertexToCellMap();

        ArrayList<Object> marriedList = new ArrayList<>();
        ArrayList<Object> adoptedList = new ArrayList<>();
        ArrayList<Object> kinList = new ArrayList<>();
        ArrayList<Object> vipList = new ArrayList<>();
        ArrayList<Object> allList = new ArrayList<>();

        graphAdapter.setConnectableEdges(false);
        graphAdapter.setCellsResizable(false);
        graphAdapter.setCellsMovable(false);
        graphAdapter.setCellsEditable(false);
        graphAdapter.setAllowDanglingEdges(false);
        mxConstants.DEFAULT_MARKERSIZE = 0;

        graphAdapter.getSelectionModel().addListener(mxEvent.CHANGE, (o, mxEventObject) -> {
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
        });
        for(Person person : vertexToCellMap.keySet()){
            if(person.isVip())
                vipList.add(vertexToCellMap.get(person));
            allList.add(vertexToCellMap.get(person));
        }
        for(RelationshipEdge edge : edgemxICellHashMap.keySet()){
            if(edge.getLabel().equals("married"))
                marriedList.add(edgemxICellHashMap.get(edge));
            if(edge.getLabel().equals("adopted"))
                adoptedList.add(edgemxICellHashMap.get(edge));
            if(edge.getLabel().equals("kin"))
                kinList.add(edgemxICellHashMap.get(edge));
        }

        this.marriedCells = new Object[marriedList.size()];
        marriedList.toArray(this.marriedCells);

        this.adoptedCells = new Object[adoptedList.size()];
        adoptedList.toArray(this.adoptedCells);

        this.kinCells = new Object[kinList.size()];
        kinList.toArray(this.kinCells);

        this.vipCells = new Object[vipList.size()];
        vipList.toArray(this.vipCells);

        this.allCells = new Object[allList.size()];
        allList.toArray(this.allCells);

        this.graphAdapter.setCellStyle("strokeColor=#6b512a;fillColor=#a88652;fontColor=#ffffff", this.allCells);
        this.graphAdapter.setCellStyle("strokeColor=#6b512a;fillColor=#a88652;fontColor=#802e2e", this.vipCells);
    }

    public JGraphXAdapter<Person, RelationshipEdge> getGraphAdapter(){
        return this.graphAdapter;
    }

    public void setCellsStyle(String cellType, Color c){
        String colorInHex = String.format("%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
        String color = "strokeColor=#" + colorInHex;
        switch (cellType){
            case "married":
                this.graphAdapter.setCellStyle(color, marriedCells);
                break;
            case "kin":
                this.graphAdapter.setCellStyle(color, kinCells);
                break;
            case "adopted":
                this.graphAdapter.setCellStyle(color+";dashed=true", adoptedCells);
                break;
        }
    }
}