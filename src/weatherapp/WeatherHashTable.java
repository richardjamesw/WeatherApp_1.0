/*
Rick Wallace
WeatherHashTable
Desc: Custom hashtable including getKey and toArray features.
 */
package weatherapp;

public class WeatherHashTable<K, V> {

    private int TAB_SIZE = 64;
    private int count;
    private WeatherHashEntry2<K,V>[] tab;

    static class WeatherHashEntry2<K, V> {

        K key;
        V value;
        WeatherHashEntry2<K,V> next;

        public WeatherHashEntry2(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    WeatherHashTable() {
        this.count = 0;
        tab = new WeatherHashEntry2[TAB_SIZE];
//        for (int i = 0; i < TAB_SIZE; i++) {
//            tab[i] = null;
//        }
    }

    public V findVal(K key) {
        WeatherHashEntry2<K,V>[] temp = tab;
        int h = Math.abs(key.hashCode() % TAB_SIZE);
        if (temp[h] == null) {       //if not found return null
            return null;
        } else {
            WeatherHashEntry2<K,V> entry = temp[h];        //hold slot
            while (entry != null && !entry.key.equals(key)) {  //find the matching key
                entry = entry.next;
            }
            if (entry == null) {    //if no key return null
                return null;
            } else {
                return entry.value;        //if key is found return value
            }
        }
    }//end findVal

    public String findKey(V val) {
        WeatherHashEntry2<K,V>[] temp = tab;
        WeatherHashEntry2<K,V> current;
        for (int t = 0; t < temp.length; t++) { //iterate through array

            if (temp[t] != null) {
                try {
                    if (val == temp[t].value) { //if in array return it
                        return (String) temp[t].key;
                    }
                    //if not found yet, check list
                    current = temp[t];
                    while (val != current.value) {
                        current = current.next;
                    }
                    if (val == current.value) {
                        return (String) current.key;
                    }
                } catch (NullPointerException e) {
                    //System.err.println("Error: " + e);
                }
            }
        }
        return null;
    }//end findKey

    public void put(K key, V value) {
        //hash string
        int h = Math.abs(key.hashCode() % TAB_SIZE);
        //create new entry
        WeatherHashEntry2<K,V> entry = new WeatherHashEntry2<K,V>(key, value);

        //insert entry to hash array
        if (tab[h] == null) {
            //no collision insert entry
            tab[h] = entry;
            count++; //inc count after adding new entry

        } else {
            //detected collision, step through list
            WeatherHashEntry2<K,V> current = tab[h];
            while (current.next != null) {
                //check keys
                if (current.key.equals(entry.key)) {
                    //replace current value
                    current.value = entry.value;
                    return;
                }
                current = current.next;
            }
            //next is null insert node
            current.next = entry;
            count++; //inc count after adding new entry

        }
    }

    public int size() {
        return count;
    }

    public void expandCapacity() {

    }

    public State[] toStateArray() {
        State[] newArr = new State[count];
        int c = 0;
        WeatherHashEntry2<K,V>[] temp = tab;
        WeatherHashEntry2<K,V> current;

        for (int t = 0; t < temp.length; t++) { //iterate through array

            if (temp[t] != null) {
                try {
                    newArr[c] = (State) temp[t].value;
                    c++;
                    current = temp[t];

                    while (current.next != null) {
                        current = current.next;
                        newArr[c] = (State) current.value;
                        c++;
                    }

                } catch (NullPointerException e) {
                    //System.err.println("Error: " + e);
                }
            }
        }
        return newArr;
    } //end toStateArray
    
    public Station[] toStationArray() {
        Station[] newArr = new Station[count];
        int c = 0;
        WeatherHashEntry2<K,V>[] temp = tab;
        WeatherHashEntry2<K,V> current;

        for (int t = 0; t < temp.length; t++) { //iterate through array

            if (temp[t] != null) {
                try {
                    newArr[c] = (Station) temp[t].value;
                    c++;
                    current = temp[t];

                    while (current.next != null) {
                        current = current.next;
                        newArr[c] = (Station) current.value;
                        c++;
                    }

                } catch (NullPointerException e) {
                    //System.err.println("Error: " + e);
                }
            }
        }
        return newArr;
    } //end toArray

}
