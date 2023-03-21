package graphica;

import Data.SpaceMarine;
import Exceptions.FiledIncorrect;
import aplicattion.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AutoUpdater extends Thread {
    public LinkedList<SpaceMarine> spaceMarines = new LinkedList<>();
    public LinkedList<SpaceMarine> spaceMarines1 = new LinkedList<>();
    public List<Integer> users_keys = new ArrayList<>();
    public MainController maint;
    private ObservableList<SpaceMarine> masterData = FXCollections.observableArrayList();

    public AutoUpdater(MainController maint) {
        this.maint = maint;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                updateData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FiledIncorrect filedIncorrect) {
                filedIncorrect.printStackTrace();
            }
        }
    }

    public void updateData() throws InterruptedException, FiledIncorrect {
        MainApp.client.executeCommand("upda");
        spaceMarines1.clear();
        spaceMarines.clear();
        spaceMarines1.addAll(maint.persons);
        SpaceMarine h;
        this.sleep(3000);
        users_keys.clear();
        for (SpaceMarine space : MainApp.space) {
            users_keys.add(space.getOwner().getLogin().length() % 7);
            h = new SpaceMarine(space.getId(), space.getName(), space.getCoordinates().getX(), space.getCoordinates().getY(), space.getCreationDate(), space.getHealth(), space.getHeight(), space.getCategory().toString(), space.getWeaponType().toString(), space.getChapter().getName(), space.getChapter().getParentLegion(), space.getChapter().getMarinesCount(), space.getChapter().getWorld(), space.getOwner());
            spaceMarines.add(h);
        }
        if (spaceMarines.size() > spaceMarines1.size() && (spaceMarines.size() - spaceMarines1.size() == 1)) {
            for (int i = 0; i < spaceMarines1.size(); i++) {
                SpaceMarine o = spaceMarines1.get(i);
                SpaceMarine p = spaceMarines.get(i);
                if (!o.getId().toString().equals(p.getId().toString())) {
                    maint.spike = spaceMarines.get(i);
                    break;
                }
                maint.spike = spaceMarines.get(spaceMarines.size() - 1);
            }
            maint.addUpdate = true;
            masterData.clear();
            masterData.addAll(spaceMarines);
            FilteredList<SpaceMarine> filteredData = new FilteredList<>(masterData, p -> true);
            maint.createListeners(filteredData);
            SortedList<SpaceMarine> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(maint.dbTable.comparatorProperty());
            maint.persons.clear();
            maint.users_keys.clear();
            maint.dbTable.setItems(sortedData);
            maint.users_keys.addAll(users_keys);
            maint.persons.addAll(spaceMarines);
            spaceMarines.clear();
        } else if (spaceMarines.size() < spaceMarines1.size()) {
            for (int i = 0; i < spaceMarines.size(); i++) {
                SpaceMarine o = spaceMarines1.get(i);
                SpaceMarine p = spaceMarines.get(i);
                if (!o.getId().toString().equals(p.getId().toString())) {
                    maint.spike = spaceMarines1.get(i);
                    break;
                }
                maint.spike = spaceMarines1.get(spaceMarines1.size() - 1);
            }
            maint.removeUpdate = true;
            masterData.clear();
            masterData.addAll(spaceMarines);
            FilteredList<SpaceMarine> filteredData = new FilteredList<>(masterData, p -> true);
            maint.createListeners(filteredData);
            SortedList<SpaceMarine> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(maint.dbTable.comparatorProperty());
            maint.persons.clear();
            maint.users_keys.clear();
            maint.dbTable.setItems(sortedData);
            maint.users_keys.addAll(users_keys);
            maint.persons.addAll(spaceMarines);
            spaceMarines.clear();
        } else if (spaceMarines.size() == spaceMarines1.size()) {
            for (int i = 0; i < spaceMarines1.size(); i++) {
                SpaceMarine o = spaceMarines1.get(i);
                SpaceMarine p = spaceMarines.get(i);
                if (!o.getName().equals(p.getName())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!o.getNames().equals(p.getNames())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!o.getLegion().equals(p.getLegion())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!o.getWorld().equals(p.getWorld())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!o.getCategory().toString().equals(p.getCategory().toString())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!o.getWeaponType().toString().equals(p.getWeaponType().toString())) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!Integer.toString((int) o.getX()).equals(Integer.toString((int) p.getX()))) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!Integer.toString(o.getY()).equals(Integer.toString(p.getY()))) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!Integer.toString((int) o.getHeight()).equals(Integer.toString((int) p.getHeight()))) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!Integer.toString(o.getHealth().intValue()).equals(Integer.toString(p.getHealth().intValue()))) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
                if (!Integer.toString(o.getCount()).equals(Integer.toString(p.getCount()))) {
                    maint.spike = spaceMarines.get(i);
                    maint.spikuchka=spaceMarines1.get(i);
                    maint.drawUpdate = true;
                    break;
                }
            }
            if (maint.drawUpdate) {

                masterData.clear();
                masterData.addAll(spaceMarines);
                FilteredList<SpaceMarine> filteredData = new FilteredList<>(masterData, p -> true);
                maint.createListeners(filteredData);
                SortedList<SpaceMarine> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(maint.dbTable.comparatorProperty());
                maint.persons.clear();
                maint.users_keys.clear();
                maint.dbTable.setItems(sortedData);
                maint.users_keys.addAll(users_keys);
                maint.persons.addAll(spaceMarines);
                spaceMarines.clear();
            }
        } else {
            masterData.clear();
            masterData.addAll(spaceMarines);
            FilteredList<SpaceMarine> filteredData = new FilteredList<>(masterData, p -> true);
            maint.createListeners(filteredData);
            SortedList<SpaceMarine> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(maint.dbTable.comparatorProperty());
            maint.persons.clear();
            maint.users_keys.clear();
            maint.dbTable.setItems(sortedData);
            maint.users_keys.addAll(users_keys);
            maint.persons.addAll(spaceMarines);
            spaceMarines.clear();
        }
    }
}
