package app.nextmobile.rickandmorty.utils;

import android.text.TextUtils;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.repos.RequestInterface;

/**
 * Manage Characters List in memory cache
 * If the Character is not in memory load from API
 */

public class CharacterManager {

    private SparseArray<Character> characters = new SparseArray();

    public void getCharacters(List<String> links,@NonNull final RequestInterface<List<Character>> delegate) {
        if (links == null || links.isEmpty())  {
            delegate.onResult(new ArrayList<>());
            return;
        }
        List<Integer> ids = getCharactersListString(links);
        if (ids.isEmpty()) {
            delegate.onResult(new ArrayList<>());
            return;
        }
        List<Character> chars = new ArrayList<>();
        List<Integer> missing = new ArrayList<>();
        for (int id : ids) {
            Character c = characters.get(id);
            if (c == null) {
                missing.add(id);
            } else {
                chars.add(c);
            }
        }
        if (missing.isEmpty()) {
            sortAndReturn(chars, delegate);
            return;
        }
        String joinedIds = TextUtils.join(",", missing);
        ServiceLocator.getInstance().getAPI().getCharacters(joinedIds, new RequestInterface<List<Character>>() {
            @Override
            public void onResult(List<Character> result) {
                if (result != null) {
                    for (Character c : result) {
                        characters.put(c.getId(), c);
                    }
                    chars.addAll(result);
                }
                sortAndReturn(chars, delegate);
            }

            @Override
            public void onError() {
                //return what already in cache
                if (chars.isEmpty()) {
                    delegate.onError();
                } else {
                    sortAndReturn(chars, delegate);
                }
            }
        });
    }

    /**
     *
     * @param id - Character ID
     * @return Character if cached in memory otherwise NULL
     */
    @Nullable
    public Character getCharacterById(int id) {
        return characters.get(id);
    }

    public void killCharacter(int id) {
        if (characters.get(id) == null || characters.get(id).getStatus() == Character.CharacterStatus.DEAD) {
            return;
        }
        characters.get(id).setStatus(Character.CharacterStatus.DEAD);
    }

    /**
     * Sort character list by date
     * @param chars List of Characters
     * @param delegate Callback
     */
    private void sortAndReturn(List<Character> chars, @NonNull final RequestInterface<List<Character>> delegate) {
        Collections.sort(chars, (c1,c2) -> c1.getCreated().compareTo(c2.getCreated()));
        delegate.onResult(chars);
    }

    /**
     * Return list of character id extract from list of character link
     * @param list
     * @return List<Int> of character id
     */
    private List<Integer> getCharactersListString(List<String> list) {
        if (list == null || list.isEmpty()) return new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        Pattern pattern = Pattern.compile("/\\d+$");
        for (String item : list) {
            Matcher matcher = pattern.matcher(item);
            if (matcher.find()) {
                ids.add(Integer.parseInt(matcher.group(0).replace("/", "")));
            }
        }
        return ids;
    }

}
