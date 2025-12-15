import json
from datetime import UTC, datetime
from pathlib import Path


def find_bestiary_files(root: Path):
    for path in root.rglob("*bestiary*.json"):
        if path.name == "bestiary-ultimate.json":
            continue
        yield path


def load_json(path: Path):
    with path.open("r", encoding="utf-8") as fp:
        return json.load(fp)


def build_output(root: Path):
    sources = []
    for file_path in find_bestiary_files(root):
        try:
            data = load_json(file_path)
        except json.JSONDecodeError as exc:
            data = {"error": f"Unable to parse JSON: {exc}"}
        sources.append(
            {
                "path": str(file_path.relative_to(root)),
                "content": data,
            }
        )

    return {
        "generated_at": datetime.now(UTC).isoformat(),
        "source_count": len(sources),
        "sources": sources,
    }


def write_output(root: Path):
    output = build_output(root)
    destination = root / "bestiary-ultimate.json"
    with destination.open("w", encoding="utf-8") as fp:
        json.dump(output, fp, indent=2, ensure_ascii=False)
        fp.write("\n")
    return destination


def main():
    repo_root = Path(__file__).resolve().parent.parent
    destination = write_output(repo_root)
    print(f"Merged bestiary data written to {destination}")


if __name__ == "__main__":
    main()
