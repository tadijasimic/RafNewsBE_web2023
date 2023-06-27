package raf.rs.rafnews_web_2023.implementation;

import raf.rs.rafnews_web_2023.model.entity.Tag;
import raf.rs.rafnews_web_2023.repository.api.TagRepositoryAPI;
import raf.rs.rafnews_web_2023.repository.mysql.MySQLRepository;

import java.util.List;

public class TagRepository extends MySQLRepository implements TagRepositoryAPI {
    @Override
    public List<Tag> allTags() {
        return null;
    }

    @Override
    public List<Tag> tagForPage(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Tag> tag(int categoryId) {
        return null;
    }
}
